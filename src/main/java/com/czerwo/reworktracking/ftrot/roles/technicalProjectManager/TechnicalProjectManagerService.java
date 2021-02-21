package com.czerwo.reworktracking.ftrot.roles.technicalProjectManager;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.auth.ApplicationUserRepository;
import com.czerwo.reworktracking.ftrot.models.data.Team;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageTasksDto;
import com.czerwo.reworktracking.ftrot.models.mappers.TaskMapper;
import com.czerwo.reworktracking.ftrot.models.mappers.WorkPackageTasksMapper;
import com.czerwo.reworktracking.ftrot.models.repositories.TaskRepository;
import com.czerwo.reworktracking.ftrot.models.repositories.TeamRepository;
import com.czerwo.reworktracking.ftrot.models.repositories.WorkPackageRepository;
import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TechnicalProjectManagerService {

    private final WorkPackageRepository workPackageRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final TaskRepository taskRepository;
    private final TeamRepository teamRepository;
    private final WorkPackageTasksMapper workPackageTasksMapper;
    private final TaskMapper taskMapper;


    public TechnicalProjectManagerService(WorkPackageRepository workPackageRepository,
                                          ApplicationUserRepository applicationUserRepository,
                                          TaskRepository taskRepository,
                                          TeamRepository teamRepository, WorkPackageTasksMapper workPackageTasksMapper,
                                          TaskMapper taskMapper) {
        this.workPackageRepository = workPackageRepository;
        this.applicationUserRepository = applicationUserRepository;
        this.taskRepository = taskRepository;
        this.teamRepository = teamRepository;
        this.workPackageTasksMapper = workPackageTasksMapper;
        this.taskMapper = taskMapper;
    }


    public List<WorkPackageTasksDto> findAllWorkPackagesByOwnerUsername(String ownerUsername) {

        return workPackageRepository.findAllByOwnerUsernameWithTasks(ownerUsername)
                .stream()
                .map((workPackage) -> workPackageTasksMapper.toDto(workPackage, workPackage.getTasks()
                        .stream()
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());

    }


    public WorkPackageDto createWorkPackage(String owner, WorkPackageDto workpackageDto) {
        ApplicationUser assignedTechnicalProjectManagerByUsername =
                applicationUserRepository
                .findByUsername(owner)
                .orElseThrow(() -> new UsernameNotFoundException(owner));

        ApplicationUser AssignedLeadEngineerByUsername =
                applicationUserRepository
                .findByUsername(workpackageDto.getLeadEngineerUsername())
                .orElseThrow(() -> new UsernameNotFoundException(workpackageDto.getLeadEngineerUsername()));

        //todo check if leadEngineer has lead engineer role

        Team team =
                teamRepository
                        .findByName(workpackageDto.getTeamName())
                        .orElseThrow(() -> new RuntimeException());

        WorkPackage workPackageEntity = WorkPackageMapper.toEntity(workpackageDto);
        workPackageEntity.setAssignedTechnicalProjectManager(assignedTechnicalProjectManagerByUsername);
        workPackageEntity.setAssignedLeadEngineer(AssignedLeadEngineerByUsername);
        workPackageEntity.setTeam(team);

        return mapAndSave(workPackageEntity);
    }


//    public void deleteWorkPackage(String owner, Long workPackageId) {
//
//        ApplicationUser ownerByUsername = applicationUserRepository
//                .findByUsername(owner)
//                .orElseThrow(() -> new UsernameNotFoundException(owner));
//
//        WorkPackage workPackageById = workPackageRepository
//                .findById(workPackageId)
//                .orElseThrow(() -> new WorkPackageNotFoundException(workPackageId));
//
////        if (!workPackageById.getOwner().getUsername().equals(owner)) throw new UserIsNotOwnerException();
//
//        workPackageRepository.deleteById(workPackageId);
//    }

//    public WorkPackageTasksDto editWorkPackage(String owner, WorkPackageTasksDto workPackageTasksDto) {
//
//        ApplicationUser ownerByUsername = applicationUserRepository
//                .findByUsername(owner)
//                .orElseThrow(() -> new UsernameNotFoundException(owner));
//
//        WorkPackage workPackageById = workPackageRepository
//                .findById(workPackageTasksDto.getId())
//                .orElseThrow(() -> new WorkPackageNotFoundException(workPackageTasksDto.getId()));
//
////        if (!workPackageById.getOwner().getUsername().equals(owner)) throw new UserIsNotOwnerException();
//
//        WorkPackage updatedWorkPackage = updateWorkPackage(workPackageById, workPackageTasksDto);
//
//        return mapAndSave(updatedWorkPackage);
//    }


    private WorkPackageDto mapAndSave(WorkPackage workPackage) {
        WorkPackage savedWorkPackage = workPackageRepository.save(workPackage);
        return WorkPackageMapper.toDto(savedWorkPackage);
    }

    private WorkPackage updateWorkPackage(WorkPackage entity, WorkPackageTasksDto dto) {
        //TODO CHECK FOR NULLS
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());

//        ApplicationUser leadEngineerByName = applicationUserRepository
//                .findByUsername(dto.getLeadEngineer())
//                .orElseThrow(() -> new UsernameNotFoundException(dto.getLeadEngineer()));
//        entity.setAssignedLeadEngineer(leadEngineerByName);
//
//        ApplicationUser teamLeaderByName = applicationUserRepository
//                .findByUsername(dto.getTeamLeader())
//                .orElseThrow(() -> new UsernameNotFoundException(dto.getLeadEngineer()));
//        entity.setAssignedLeadEngineer(leadEngineerByName);


        return entity;
    }



}
