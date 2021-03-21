package com.czerwo.reworktracking.ftrot.models.mappers;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.auth.ApplicationUserRepository;
import com.czerwo.reworktracking.ftrot.models.data.Day.Day;
import com.czerwo.reworktracking.ftrot.models.data.Task;
import com.czerwo.reworktracking.ftrot.models.data.UserInfo;
import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;
import com.czerwo.reworktracking.ftrot.models.dtos.DayDto;
import com.czerwo.reworktracking.ftrot.models.dtos.TaskDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageTasksDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkPackageTasksMapper {

    private final ApplicationUserRepository applicationUserRepository;
    private final TaskMapper taskMapper;

    public WorkPackageTasksMapper(ApplicationUserRepository applicationUserRepository, TaskMapper taskMapper) {
        this.applicationUserRepository = applicationUserRepository;
        this.taskMapper = taskMapper;
    }

    public WorkPackageTasksDto toDto(Optional<WorkPackage> workPackage, List<Task> tasks){

        WorkPackageTasksDto dto = new WorkPackageTasksDto();

        List<TaskDto> taskDtos = tasks
                .stream()
                .map(task -> {

                    LocalDate date = Optional.ofNullable(task)
                            .map(Task::getDay)
                            .map(Day::getDate)
                            .orElseGet(() -> LocalDate.MAX);
                    String engineerSurname = Optional.ofNullable(task)
                            .map(Task::getAssignedEngineer)
                            .map(ApplicationUser::getUserInfo)
                            .map(UserInfo::getSurname)
                            .orElseGet(() ->"Not assigned");


                    return taskMapper.toDto(task, date, engineerSurname);
                })
                .collect(Collectors.toList());

        long finishedTasksQuantity = tasks
                .stream()
                .filter(task -> task.getStatus() == 1)
                .count();

        Optional<LocalDate> lastTask = tasks
                .stream()
                .map(Task::getDay)
                .map(day  -> day == null ? null : day.getDate())
                .map(date -> date == null ? LocalDate.MAX : date)
                .max((date1, date2) -> {
                    if (date1.isAfter(date2)) return 1;
                    if (date1.isBefore(date2)) return -1;
                    return 0;
                });


        LocalDate predictedDueTo = lastTask.orElseGet(() -> LocalDate.MAX);


        taskDtos
                .stream()
                .forEach(taskDto -> {taskDto.setWorkPackageName(workPackage.map(WorkPackage::getName).orElseGet(() -> ""));});

        dto.setId(workPackage.map(WorkPackage::getId).orElseGet(null));
        dto.setDescription(workPackage.map(WorkPackage::getDescription).orElseGet(() -> ""));
        dto.setName(workPackage.map(WorkPackage::getName).orElseGet(() -> ""));
        dto.setStartDate(workPackage.map(WorkPackage::getStartDate).orElseGet(() -> LocalDate.now()));
        dto.setStatus(workPackage.map(WorkPackage::getStatus).orElseGet(() -> 0d));
        dto.setPredictedFinish(predictedDueTo);
        dto.setDeadline(workPackage.map(WorkPackage::getDeadline).orElseGet(() -> LocalDate.now()));
        dto.setLeadEngineerName(workPackage
                .map(WorkPackage::getAssignedLeadEngineer)
                .map(ApplicationUser::getUserInfo)
                .map(UserInfo::getName)
                .orElseGet(() -> ""));

        dto.setLeadEngineerSurname(workPackage
                .map(WorkPackage::getAssignedLeadEngineer)
                .map(ApplicationUser::getUserInfo)
                .map(UserInfo::getSurname)
                .orElseGet(() -> ""));

        dto.setLeadEngineerUsername(workPackage
                .map(WorkPackage::getAssignedLeadEngineer)
                .map(ApplicationUser::getUsername)
                .orElseGet(() -> ""));
        dto.setTasks(taskDtos);
        dto.setTasksQuantity(taskDtos.size());
        dto.setFinishedTasks((int)finishedTasksQuantity);


        return  dto;
    }

    public WorkPackage toEntity(WorkPackageTasksDto workPackageTasksDto){
        WorkPackage entity = new WorkPackage();

        entity.setId(workPackageTasksDto.getId());
        entity.setName(workPackageTasksDto.getName());
        entity.setDescription(workPackageTasksDto.getDescription());

   //     Optional<ApplicationUser> owner = applicationUserRepository.findByUsername(workPackageDto.getOwner());
//        owner.ifPresent(entity::setOwner);

   //     Optional<ApplicationUser> leadEngineer = applicationUserRepository.findByUsername(workPackageDto.getLeadEngineer());
    //    leadEngineer.ifPresent(entity::setAssignedLeadEngineer);

    //    Optional<ApplicationUser> teamLeader = applicationUserRepository.findByUsername(workPackageDto.getOwner());
//        teamLeader.ifPresent(entity::setAssignedTeamLeader);

//        entity.setStart(workPackageDto.getStart());
//        entity.setEnd(workPackageDto.getEnd());


        return entity;
    }
}
