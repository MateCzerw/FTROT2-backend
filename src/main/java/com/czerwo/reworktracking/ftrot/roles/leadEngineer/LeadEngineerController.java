package com.czerwo.reworktracking.ftrot.roles.leadEngineer;

import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageTasksDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lead-engineer")
public class LeadEngineerController {

    private final LeadEngineerService leadEngineerService;

    public LeadEngineerController(LeadEngineerService leadEngineerService) {
        this.leadEngineerService = leadEngineerService;
    }

    @GetMapping("/work-packages")
    public List<WorkPackageTasksDto> findAllWorkPackagesByAssignedLeadEngineer(Principal principal) {
        return leadEngineerService
                .findAllWorkPackagesByAssignedLeadEngineer(principal.getName());
    }

    /*
    TO GET ASSIGMENT WORKPACKAGES


    TO ADD TASK FROM OWN WORKPACKAGES
        TO SET TIME RESPONSIBLE FOR WORKPACKAGE

    TO DELETE TASK FROM OWN WORKPACKAGES

    TO EDIT TASK FROM OWN WORKPACKAGES

     */



}
