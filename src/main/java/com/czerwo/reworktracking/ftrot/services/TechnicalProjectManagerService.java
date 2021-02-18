package com.czerwo.reworktracking.ftrot.services;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.auth.ApplicationUserRepository;
import com.czerwo.reworktracking.ftrot.models.dtos.TaskDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageDto;
import com.czerwo.reworktracking.ftrot.models.exceptions.User.UserIsNotOwnerException;
import com.czerwo.reworktracking.ftrot.models.exceptions.WorkPackage.WorkPackageNotFoundException;
import com.czerwo.reworktracking.ftrot.models.mappers.TaskMapper;
import com.czerwo.reworktracking.ftrot.models.mappers.WorkPackageMapper;
import com.czerwo.reworktracking.ftrot.models.repositories.TaskRepository;
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
    private final WorkPackageMapper workPackageMapper;
    private final TaskMapper taskMapper;


    public TechnicalProjectManagerService(WorkPackageRepository workPackageRepository,
                                          ApplicationUserRepository applicationUserRepository,
                                          TaskRepository taskRepository,
                                          WorkPackageMapper workPackageMapper,
                                          TaskMapper taskMapper) {
        this.workPackageRepository = workPackageRepository;
        this.applicationUserRepository = applicationUserRepository;
        this.taskRepository = taskRepository;
        this.workPackageMapper = workPackageMapper;
        this.taskMapper = taskMapper;
    }


    public List<WorkPackageDto> findAllWorkPackagesByOwnerUsername(String ownerUsername) {

//        return workPackageRepository.findAllByOwnerUsername(ownerUsername)
//                .stream()
//                .map(workPackageMapper::toDto)
//                .collect(Collectors.toList());
        return null;
    }

    public List<TaskDto> findAllTasksFromWorkPackage(String owner, long workPackageId) {

        ApplicationUser ownerByUsername = applicationUserRepository
                .findByUsername(owner)
                .orElseThrow(() -> new UsernameNotFoundException(owner));

        WorkPackage workPackageById = workPackageRepository
                .findById(workPackageId)
                .orElseThrow(() -> new WorkPackageNotFoundException(workPackageId));

//        if (!workPackageById.getOwner().getUsername().equals(owner)) throw new UserIsNotOwnerException();

        return taskRepository.findAllByWorkPackageId(workPackageId)
                .stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    public WorkPackageDto createWorkPackage(String owner, WorkPackageDto workpackageDto) {
        ApplicationUser ownerByUsername = applicationUserRepository
                .findByUsername(owner)
                .orElseThrow(() -> new UsernameNotFoundException(owner));

        WorkPackage workPackageEntity = workPackageMapper.toEntity(workpackageDto);
//        workPackageEntity.setOwner(ownerByUsername);

        return mapAndSave(workPackageEntity);
    }


    public void deleteWorkPackage(String owner, Long workPackageId) {

        ApplicationUser ownerByUsername = applicationUserRepository
                .findByUsername(owner)
                .orElseThrow(() -> new UsernameNotFoundException(owner));

        WorkPackage workPackageById = workPackageRepository
                .findById(workPackageId)
                .orElseThrow(() -> new WorkPackageNotFoundException(workPackageId));

//        if (!workPackageById.getOwner().getUsername().equals(owner)) throw new UserIsNotOwnerException();

        workPackageRepository.deleteById(workPackageId);
    }

    public WorkPackageDto editWorkPackage(String owner, WorkPackageDto workPackageDto) {

        ApplicationUser ownerByUsername = applicationUserRepository
                .findByUsername(owner)
                .orElseThrow(() -> new UsernameNotFoundException(owner));

        WorkPackage workPackageById = workPackageRepository
                .findById(workPackageDto.getId())
                .orElseThrow(() -> new WorkPackageNotFoundException(workPackageDto.getId()));

//        if (!workPackageById.getOwner().getUsername().equals(owner)) throw new UserIsNotOwnerException();

        WorkPackage updatedWorkPackage = updateWorkPackage(workPackageById, workPackageDto);

        return mapAndSave(updatedWorkPackage);
    }


    private WorkPackageDto mapAndSave(WorkPackage workPackage) {
        WorkPackage savedWorkPackage = workPackageRepository.save(workPackage);
        return workPackageMapper.toDto(savedWorkPackage);
    }

    private WorkPackage updateWorkPackage(WorkPackage entity, WorkPackageDto dto) {
        //TODO CHECK FOR NULLS
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());

        ApplicationUser leadEngineerByName = applicationUserRepository
                .findByUsername(dto.getLeadEngineer())
                .orElseThrow(() -> new UsernameNotFoundException(dto.getLeadEngineer()));
        entity.setAssignedLeadEngineer(leadEngineerByName);

        ApplicationUser teamLeaderByName = applicationUserRepository
                .findByUsername(dto.getTeamLeader())
                .orElseThrow(() -> new UsernameNotFoundException(dto.getLeadEngineer()));
        entity.setAssignedLeadEngineer(leadEngineerByName);


        return entity;
    }


}
