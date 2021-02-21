package com.czerwo.reworktracking.ftrot.roles.technicalProjectManager;

import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageTasksDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/technical-project-manager")
public class TechnicalProjectManagerController {

    private final TechnicalProjectManagerService technicalProjectManagerService;

    public TechnicalProjectManagerController(TechnicalProjectManagerService technicalProjectManagerService) {
        this.technicalProjectManagerService = technicalProjectManagerService;
    }



    @GetMapping("/work-packages")
    public List<WorkPackageTasksDto> findAllWorkPackagesByOwnerUsername(Principal principal) {
        return technicalProjectManagerService
                .findAllWorkPackagesByOwnerUsername(principal.getName());
    }


    @PostMapping("/work-packages")
    public ResponseEntity<WorkPackageDto> createWorkPackage(Principal principal, @RequestBody WorkPackageDto workPackage) {
        WorkPackageDto createdWorkPackage = technicalProjectManagerService.createWorkPackage(principal.getName(), workPackage);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdWorkPackage.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdWorkPackage);
    }
//
//    @DeleteMapping("/work-package/delete/{workPackageId}")
//    public ResponseEntity<Void> deleteWorkPackage(Principal principal, @PathVariable Long workPackageId) {
//        technicalProjectManagerService.deleteWorkPackage(principal.getName(), workPackageId);
//
//        return ResponseEntity.noContent().build();
//    }
//
//    @PutMapping("/work-package/edit/{workPackageId}")
//    public ResponseEntity<WorkPackageTasksDto>  editWorkPackage(Principal principal, @PathVariable Long workPackageId, @RequestBody WorkPackageTasksDto workPackageTasksDto) {
//        workPackageTasksDto.setId(workPackageId);
//
//        WorkPackageTasksDto updatedWorkPackage = technicalProjectManagerService.editWorkPackage(principal.getName(), workPackageTasksDto);
//
//        return ResponseEntity.ok().body(updatedWorkPackage);
//    }

}
