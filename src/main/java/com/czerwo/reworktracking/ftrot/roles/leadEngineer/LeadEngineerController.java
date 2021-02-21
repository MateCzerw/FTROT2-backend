package com.czerwo.reworktracking.ftrot.roles.leadEngineer;

import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageTasksDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/work-packages/{workPackageId}/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(Principal principal, @PathVariable Long workPackageId, @PathVariable Long taskId) {
        leadEngineerService.deleteTask(principal.getName(), workPackageId, taskId);

        return ResponseEntity.noContent().build();
    }


}
