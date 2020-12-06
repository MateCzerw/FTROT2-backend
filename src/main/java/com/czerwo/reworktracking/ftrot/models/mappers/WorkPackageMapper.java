package com.czerwo.reworktracking.ftrot.models.mappers;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.auth.ApplicationUserRepository;
import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkPackageMapper {

    private final ApplicationUserRepository applicationUserRepository;

    public WorkPackageMapper(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public WorkPackageDto toDto(WorkPackage workPackage){
        WorkPackageDto dto = new WorkPackageDto();

        dto.setOwner(workPackage.getOwner().getUsername());
        dto.setDescription(workPackage.getDescription());
        dto.setId(workPackage.getId());
        dto.setName(workPackage.getName());
        dto.setLeadEngineer(workPackage.getAssignedLeadEngineer().getUsername());
        dto.setTeamLeader(workPackage.getAssignedTeamLeader().getUsername());

        return  dto;
    }

    public WorkPackage toEntity(WorkPackageDto workPackageDto){
        WorkPackage entity = new WorkPackage();

        entity.setId(workPackageDto.getId());
        entity.setName(workPackageDto.getName());
        entity.setDescription(workPackageDto.getDescription());

        Optional<ApplicationUser> owner = applicationUserRepository.findByUsername(workPackageDto.getOwner());
        owner.ifPresent(entity::setOwner);

        Optional<ApplicationUser> leadEngineer = applicationUserRepository.findByUsername(workPackageDto.getLeadEngineer());
        leadEngineer.ifPresent(entity::setAssignedLeadEngineer);

        Optional<ApplicationUser> teamLeader = applicationUserRepository.findByUsername(workPackageDto.getOwner());
        teamLeader.ifPresent(entity::setAssignedTeamLeader);

        entity.setStart(workPackageDto.getStart());
        entity.setEnd(workPackageDto.getEnd());


        return entity;
    }
}
