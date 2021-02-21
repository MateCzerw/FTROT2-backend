package com.czerwo.reworktracking.ftrot.roles.leadEngineer;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.auth.ApplicationUserRepository;
import com.czerwo.reworktracking.ftrot.models.data.Task;
import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageTasksDto;
import com.czerwo.reworktracking.ftrot.models.mappers.WorkPackageTasksMapper;
import com.czerwo.reworktracking.ftrot.models.repositories.TaskRepository;
import com.czerwo.reworktracking.ftrot.models.repositories.WorkPackageRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeadEngineerService {

    private final WorkPackageRepository workPackageRepository;
    private final TaskRepository taskRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final WorkPackageTasksMapper workPackageTasksMapper;


    public LeadEngineerService(WorkPackageRepository workPackageRepository, TaskRepository taskRepository, ApplicationUserRepository applicationUserRepository, WorkPackageTasksMapper workPackageTasksMapper) {
        this.workPackageRepository = workPackageRepository;
        this.taskRepository = taskRepository;
        this.applicationUserRepository = applicationUserRepository;
        this.workPackageTasksMapper = workPackageTasksMapper;
    }

    public List<WorkPackageTasksDto> findAllWorkPackagesByAssignedLeadEngineer(String username) {
        return workPackageRepository.findAllWorkPackagesByAssignedLeadEngineer(username)
                .stream()
                .map((workPackage) -> workPackageTasksMapper.toDto(workPackage, workPackage.getTasks()
                        .stream()
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());

    }

    public void deleteTask(String username, Long workPackageId, Long taskId) {

        ApplicationUser userByUsername = applicationUserRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        WorkPackage workPackage = workPackageRepository
                .findByIdWithAssignedLeadEngineerAndTasks(workPackageId)
                .orElseThrow(() -> new RuntimeException());

        //todo to check if user is an assigned lead engineer
        if(!workPackage.getAssignedLeadEngineer().getUsername().equals(username)) throw new RuntimeException();
        //todo to check if task belongs to workpackage
        boolean isTaskWithinWorkPackage = workPackage.getTasks().stream().anyMatch(task -> task.getId() == taskId);

        if(!isTaskWithinWorkPackage) throw new RuntimeException();

        taskRepository.deleteById(taskId);
    }
}
