package com.czerwo.ftrot.roles.technicalProjectManager;

import com.czerwo.ftrot.models.data.WorkPackage;

import java.time.LocalDate;

class WorkPackageMapper {

    static WorkPackage toEntity(WorkPackageDto dto) {
        WorkPackage workPackage = new WorkPackage();
        workPackage.setName(dto.getName());
        workPackage.setDescription(dto.getDescription());
        workPackage.setStartDate(LocalDate.now());
        workPackage.setDeadline(dto.getDeadline());

        return workPackage;
    }

    public static WorkPackageDto toDto(WorkPackage entity) {
        WorkPackageDto dto = new WorkPackageDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setLeadEngineerUsername(entity.getAssignedLeadEngineer().getUsername());
        dto.setTeamName(entity.getTeam().getName());
        dto.setDeadline(entity.getDeadline());

        return dto;
    }
}
