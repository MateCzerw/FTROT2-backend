package com.czerwo.reworktracking.ftrot.roles.technicalProjectManager;

import com.czerwo.reworktracking.ftrot.models.dtos.TaskDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/technical-project-manager")
public class TechnicalProjectManagerController {

    private TechnicalProjectManagerService technicalProjectManagerService;

    public TechnicalProjectManagerController(TechnicalProjectManagerService technicalProjectManagerService) {
        this.technicalProjectManagerService = technicalProjectManagerService;
    }

    /*
    EACH WORK PACKAGE SHOULD HAVE:
        Done:LEAD ENGINEER - RESPONSIBLE FOR TASKS
        Done:TEAM LEADER - RESPONSIBLE FOR TASKS ASSIGMENT TO PEOPLE


    GET ALL OWN WORK PACKAGE
        - LIST OF TASKS
        - CHECK STATUS OF TASKS
        - CHECK PREDICTED TIME FINISH

    Done:CREATE WORK PACKAGE
    Done:EDIT WORK PACKAGE
    Done:DELETE WORK PACKAGE
     */

    @GetMapping("/all")
    public List<WorkPackageDto> findAllWorkPackagesByOwnerUsername(Principal principal) {
        return technicalProjectManagerService.findAllWorkPackagesByOwnerUsername(principal.getName());
    }

    @GetMapping("/work-package/{id}/tasks")
    public List<TaskDto> findAllTasksFromWorkPackage(Principal principal, @PathVariable long id) {
        return technicalProjectManagerService
                .findAllTasksFromWorkPackage(principal.getName(),id);
    }



//    @GetMapping("/error")
//    public List<WorkPackageDto> throwError(Principal principal) {
//        throw new WorkPackageNotFoundException();
//    }


    @PostMapping("/work-package/create")
    public ResponseEntity<WorkPackageDto> createWorkPackage(Principal principal, @RequestBody WorkPackageDto workPackage) {
        WorkPackageDto createdWorkPackage = technicalProjectManagerService.createWorkPackage(principal.getName(), workPackage);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdWorkPackage.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdWorkPackage);
    }

    @DeleteMapping("/work-package/delete/{workPackageId}")
    public ResponseEntity<Void> deleteWorkPackage(Principal principal, @PathVariable Long workPackageId) {
        technicalProjectManagerService.deleteWorkPackage(principal.getName(), workPackageId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/work-package/edit/{workPackageId}")
    public ResponseEntity<WorkPackageDto>  editWorkPackage(Principal principal, @PathVariable Long workPackageId, @RequestBody WorkPackageDto workPackageDto) {
        workPackageDto.setId(workPackageId);

        WorkPackageDto updatedWorkPackage = technicalProjectManagerService.editWorkPackage(principal.getName(), workPackageDto);

        return ResponseEntity.ok().body(updatedWorkPackage);
    }

}
