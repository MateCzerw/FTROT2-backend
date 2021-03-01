package com.czerwo.reworktracking.ftrot.roles.leadEngineer;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.auth.ApplicationUserRepository;
import com.czerwo.reworktracking.ftrot.models.data.Task;
import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageSimplifiedDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageStatusDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageTasksDto;
import com.czerwo.reworktracking.ftrot.models.mappers.WorkPackageSimplifiedMapper;
import com.czerwo.reworktracking.ftrot.models.mappers.WorkPackageTasksMapper;
import com.czerwo.reworktracking.ftrot.models.repositories.TaskRepository;
import com.czerwo.reworktracking.ftrot.models.repositories.WorkPackageRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeadEngineerService {

    private final WorkPackageRepository workPackageRepository;
    private final TaskRepository taskRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final WorkPackageTasksMapper workPackageTasksMapper;
    private final WorkPackageSimplifiedMapper workPackageSimplifiedMapper;


    public LeadEngineerService(WorkPackageRepository workPackageRepository, TaskRepository taskRepository, ApplicationUserRepository applicationUserRepository, WorkPackageTasksMapper workPackageTasksMapper, WorkPackageSimplifiedMapper workPackageSimplifiedMapper) {
        this.workPackageRepository = workPackageRepository;
        this.taskRepository = taskRepository;
        this.applicationUserRepository = applicationUserRepository;
        this.workPackageTasksMapper = workPackageTasksMapper;

        this.workPackageSimplifiedMapper = workPackageSimplifiedMapper;
    }

    public List<WorkPackageTasksDto> findAllWorkPackagesByAssignedLeadEngineer(String username) {
        return workPackageRepository.findAllWorkPackagesByAssignedLeadEngineerWithTasks(username)
                .stream()
                .map((workPackage) -> workPackageTasksMapper.toDto(workPackage, workPackage.getTasks()
                        .stream()
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());

    }

    @Transactional
    public void deleteTask(String username, Long workPackageId, Long taskId) {

        ApplicationUser userByUsername = applicationUserRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        WorkPackage workPackage = workPackageRepository
                .findByIdWithAssignedLeadEngineerAndTasks(workPackageId)
                .orElseThrow(() -> new RuntimeException());

        //todo to check if user is an assigned lead engineer
        if (!workPackage.getAssignedLeadEngineer().getUsername().equals(username)) throw new RuntimeException();
        //todo to check if task belongs to workpackage
        boolean isTaskWithinWorkPackage = workPackage.getTasks().stream().anyMatch(task -> task.getId() == taskId);

        Task taskById = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException());
        workPackage.removeTask(taskById);

        taskRepository.deleteById(taskId);

    }

    public TaskDto createTask(String leadEngineerUsername, TaskDto taskDto, long workPackageId) {

        WorkPackage workPackageById = workPackageRepository
                .findByIdWithAssignedLeadEngineer(workPackageId)
                .orElseThrow(() -> new RuntimeException());

        ApplicationUser leadEngineerByUsername =
                applicationUserRepository
                        .findByUsername(leadEngineerUsername)
                        .orElseThrow(() -> new UsernameNotFoundException(leadEngineerUsername));

        if (workPackageById.getAssignedLeadEngineer().getId() != leadEngineerByUsername.getId())
            throw new RuntimeException();

        Task taskEntity = new Task();
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setName(taskDto.getName());
        taskEntity.setDuration(taskDto.getDuration());
        taskEntity.setWorkPackage(workPackageById);
        taskEntity.setStatus(0);

        return mapAndSave(taskEntity);

    }

    public TaskDto editTask(String leadEngineerUsername, TaskDto taskDto, Long workPackageId) {
        WorkPackage workPackageById = workPackageRepository
                .findByIdWithAssignedLeadEngineerAndTasks(workPackageId)
                .orElseThrow(() -> new RuntimeException());

        ApplicationUser leadEngineerByUsername =
                applicationUserRepository
                        .findByUsername(leadEngineerUsername)
                        .orElseThrow(() -> new UsernameNotFoundException(leadEngineerUsername));
        if (workPackageById.getAssignedLeadEngineer().getId() != leadEngineerByUsername.getId())
            throw new RuntimeException();

        boolean isTaskAssignedToWorkPackage = workPackageById
                .getTasks()
                .stream()
                .anyMatch(task -> task.getId() == taskDto.getId());

        if (!isTaskAssignedToWorkPackage) throw new RuntimeException();

        Task entity = taskRepository.findById(taskDto.getId()).orElseThrow(() -> new RuntimeException());
        entity.setName(taskDto.getName());
        entity.setDuration(taskDto.getDuration());
        entity.setDescription(taskDto.getDescription());

        return mapAndSave(entity);

    }


    private TaskDto mapAndSave(Task task) {
        Task savedTask = taskRepository.save(task);
        return TaskMapper.toDto(savedTask);
    }


    public UserInfoDto getUserInfoByUsername(String username) {

        Optional<ApplicationUser> userByUsername = applicationUserRepository
                .findByUsernameWithTeamAndUserInfo(username);

        return UserInfoMapper.toDto(userByUsername);
    }

    public List<WorkPackageSimplifiedDto> getTopFiveWorkPackageWithClosestDeadline(String username){

        Pageable topFive = PageRequest.of(0, 5);

        List<WorkPackageSimplifiedDto> workPackagesDto = workPackageRepository.findTop5ByAssignedLeadEngineerUsernameAndOrderByDeadlineAsc(username, topFive)
                .stream()
                .map(workPackageSimplifiedMapper::toDto)
                .collect(Collectors.toList());

        return workPackagesDto;
    }


    public WorkPackageStatusDto getWorkPackagesStatus(String username) {

        LocalDate currentDate = LocalDate.now();
        WorkPackageStatusDto workPackageStatusDto = new WorkPackageStatusDto();

        List<WorkPackage> workPackages = workPackageRepository
                .findAllWorkPackagesByAssignedLeadEngineerWithTasks(username);

        long onTimeWorkPackages = workPackages
                .stream()
                .filter(workPackage -> !workPackage.isFinished())
                .filter(workPackage -> workPackage.getDeadline().isBefore(currentDate))
                .count();

        long stoppedWorkPackages = workPackages
                .stream()
                .filter(workPackage -> workPackage
                        .getTasks()
                        .stream()
                        .filter(task -> task.getDay() == null)
                        .count() > 0)
                .count();

        long delayedWorkPackages = workPackages
                .stream()
                .filter(workPackage -> !workPackage.isFinished())
                .filter(workPackage -> workPackage
                        .getDeadline()
                        .isAfter(currentDate))
                .count();


        workPackageStatusDto.setOnTime((int)onTimeWorkPackages);
        workPackageStatusDto.setStopped((int) stoppedWorkPackages);
        workPackageStatusDto.setDelayed((int) delayedWorkPackages);

        return workPackageStatusDto;
    }


}
