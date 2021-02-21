package com.czerwo.reworktracking.ftrot.models.mappers;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUserRepository;
import com.czerwo.reworktracking.ftrot.models.data.Task;
import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;
import com.czerwo.reworktracking.ftrot.models.dtos.TaskDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageTasksDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkPackageTasksMapper {

    private final ApplicationUserRepository applicationUserRepository;
    private final TaskMapper taskMapper;

    public WorkPackageTasksMapper(ApplicationUserRepository applicationUserRepository, TaskMapper taskMapper) {
        this.applicationUserRepository = applicationUserRepository;
        this.taskMapper = taskMapper;
    }

    public WorkPackageTasksDto toDto(WorkPackage workPackage, List<Task> tasks){

        WorkPackageTasksDto dto = new WorkPackageTasksDto();

        List<TaskDto> taskDtos = tasks
                .stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());


        taskDtos
                .stream()
                .forEach(taskDto -> {taskDto.setWorkPackageName(workPackage.getName());});

        dto.setId(workPackage.getId());
        dto.setDescription(workPackage.getDescription());
        dto.setName(workPackage.getName());
        dto.setStartDate(workPackage.getStartDate());
        dto.setPredictedFinish(LocalDate.now());
        dto.setDeadline(workPackage.getDeadline());
        dto.setTaskDtos(taskDtos);
        dto.setTaskFinished(999);

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
