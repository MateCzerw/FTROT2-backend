package com.czerwo.reworktracking.ftrot.roles.leadEngineer;

import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageTasksDto;
import com.czerwo.reworktracking.ftrot.models.mappers.WorkPackageTasksMapper;
import com.czerwo.reworktracking.ftrot.models.repositories.WorkPackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeadEngineerService {

    private final WorkPackageRepository workPackageRepository;
    private final WorkPackageTasksMapper workPackageTasksMapper;

    public LeadEngineerService(WorkPackageRepository workPackageRepository, WorkPackageTasksMapper workPackageTasksMapper) {
        this.workPackageRepository = workPackageRepository;
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
}
