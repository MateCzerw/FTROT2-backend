package com.czerwo.reworktracking.ftrot.models.mappers;


import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageSimplifiedDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkPackageSimplifiedMapper {

    public WorkPackageSimplifiedDto toDto(WorkPackage workPackage) {

        WorkPackageSimplifiedDto dto = new WorkPackageSimplifiedDto();
        dto.setId(workPackage.getId());
        dto.setName(workPackage.getName());
        dto.setDeadline(workPackage.getDeadline());
        dto.setStatus(workPackage.getStatus());

        return dto;
    }

}
