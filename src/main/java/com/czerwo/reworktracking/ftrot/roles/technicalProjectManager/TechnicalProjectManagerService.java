package com.czerwo.reworktracking.ftrot.roles.technicalProjectManager;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.auth.ApplicationUserRepository;
import com.czerwo.reworktracking.ftrot.models.data.Team;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageSimplifiedDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageTasksDto;
import com.czerwo.reworktracking.ftrot.models.exceptions.User.UserIsNotOwnerException;
import com.czerwo.reworktracking.ftrot.models.exceptions.WorkPackage.WorkPackageNotFoundException;
import com.czerwo.reworktracking.ftrot.models.mappers.TaskMapper;
import com.czerwo.reworktracking.ftrot.models.mappers.WorkPackageSimplifiedMapper;
import com.czerwo.reworktracking.ftrot.models.mappers.WorkPackageTasksMapper;
import com.czerwo.reworktracking.ftrot.models.repositories.TaskRepository;
import com.czerwo.reworktracking.ftrot.models.repositories.TeamRepository;
import com.czerwo.reworktracking.ftrot.models.repositories.WorkPackageRepository;
import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TechnicalProjectManagerService {

    private final WorkPackageRepository workPackageRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final TaskRepository taskRepository;
    private final TeamRepository teamRepository;
    private final WorkPackageTasksMapper workPackageTasksMapper;
    private final TaskMapper taskMapper;
    private WorkPackageSimplifiedMapper workPackageSimplifiedMapper;


    public TechnicalProjectManagerService(WorkPackageRepository workPackageRepository,
                                          ApplicationUserRepository applicationUserRepository,
                                          TaskRepository taskRepository,
                                          TeamRepository teamRepository, WorkPackageTasksMapper workPackageTasksMapper,
                                          TaskMapper taskMapper, WorkPackageSimplifiedMapper workPackageSimplifiedMapper) {
        this.workPackageRepository = workPackageRepository;
        this.applicationUserRepository = applicationUserRepository;
        this.taskRepository = taskRepository;
        this.teamRepository = teamRepository;
        this.workPackageTasksMapper = workPackageTasksMapper;
        this.taskMapper = taskMapper;
        this.workPackageSimplifiedMapper = workPackageSimplifiedMapper;
    }

    public UserInfoDto getUserInfoByUsername(String username) {

        Optional<ApplicationUser> userByUsername = applicationUserRepository
                .findByUsernameWithTeamAndUserInfo(username);

        return UserInfoMapper.toDto(userByUsername);

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


    public void deleteWorkPackage(String owner, Long workPackageId) {

        ApplicationUser ownerByUsername = applicationUserRepository
                .findByUsername(owner)
                .orElseThrow(() -> new UsernameNotFoundException(owner));

        WorkPackage workPackageById = workPackageRepository
                .findById(workPackageId)
                .orElseThrow(() -> new WorkPackageNotFoundException(workPackageId));

        if (!workPackageById.getAssignedTechnicalProjectManager().getUsername().equals(owner)) throw new UserIsNotOwnerException();

        workPackageRepository.deleteById(workPackageId);
    }

    public WorkPackageDto editWorkPackage(String owner, WorkPackageDto workPackageDto) {

        ApplicationUser ownerByUsername = applicationUserRepository
                .findByUsername(owner)
                .orElseThrow(() -> new UsernameNotFoundException(owner));

        WorkPackage workPackageById = workPackageRepository
                .findById(workPackageDto.getId())
                .orElseThrow(() -> new WorkPackageNotFoundException(workPackageDto.getId()));

        if (!workPackageById.getAssignedTechnicalProjectManager().getUsername().equals(owner)) throw new UserIsNotOwnerException();

        WorkPackage updatedWorkPackage = updateWorkPackage(workPackageById, workPackageDto);

        return mapAndSave(updatedWorkPackage);
    }




    private WorkPackage updateWorkPackage(WorkPackage entity, WorkPackageDto dto) {

        ApplicationUser leadEngineerByName = applicationUserRepository
                .findByUsername(dto.getLeadEngineerUsername())
                .orElseThrow(() -> new UsernameNotFoundException(dto.getLeadEngineerUsername()));

        Team teamByName = teamRepository
                .findByName(dto.getTeamName())
                .orElseThrow(() -> new RuntimeException());

        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setDeadline(dto.getDeadline());
        entity.setAssignedLeadEngineer(leadEngineerByName);
        //todo check if lead engineer belongs to team
        entity.setTeam(teamByName);

        return entity;
    }

    private WorkPackageDto mapAndSave(WorkPackage workPackage) {
        WorkPackage savedWorkPackage = workPackageRepository.save(workPackage);
        return WorkPackageMapper.toDto(savedWorkPackage);
    }


    public List<WorkPackageSimplifiedDto> getTopFiveWorkPackagesWithClosestDeadline(String username) {
        Pageable topFive = PageRequest.of(0, 5);

        List<WorkPackageSimplifiedDto> workPackagesDto = workPackageRepository
                .findTop5ByAssignedTechnicalProjectManagerUsernameOrderByDeadlineAsc(username, topFive)
                .stream()
                .map(workPackageSimplifiedMapper::toDto)
                .collect(Collectors.toList());

        return workPackagesDto;
    }
}
