package com.czerwo.reworktracking.ftrot.roles.leadEngineer;

import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageSimplifiedDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageStatusDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageTasksDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lead-engineer")
public class LeadEngineerController {

    private final LeadEngineerService leadEngineerService;

    public LeadEngineerController(LeadEngineerService leadEngineerService) {
        this.leadEngineerService = leadEngineerService;
    }


    @GetMapping("/board/user-info")
    public ResponseEntity<UserInfoDto> getUserInfo(Principal principal){

        UserInfoDto userInfoDto = leadEngineerService.getUserInfoByUsername(principal.getName());

        return ResponseEntity.ok().body(userInfoDto);
    }

    @GetMapping("/board/work-packages")
    public ResponseEntity<List<WorkPackageSimplifiedDto>> getTopFiveWorkPackagesWithClosestDeadline(Principal principal){

        List<WorkPackageSimplifiedDto> workPackageSimplifiedDtos =
                leadEngineerService
                        .getTopFiveWorkPackageWithClosestDeadline(principal.getName());

        return ResponseEntity.ok().body(workPackageSimplifiedDtos);
    }

    @GetMapping("/board/work-packages-status")
    public ResponseEntity<WorkPackageStatusDto> getWorkPackagesStatus(Principal principal){

        WorkPackageStatusDto workPackageStatusDto =
                leadEngineerService
                        .getWorkPackagesStatus(principal.getName());

        return ResponseEntity.ok().body(workPackageStatusDto);
    }

    @GetMapping("/work-packages")
    public List<WorkPackageTasksDto> findAllWorkPackagesByAssignedLeadEngineer(Principal principal) {
        return leadEngineerService
                .findAllWorkPackagesByAssignedLeadEngineer(principal.getName());
    }

    @PostMapping("/work-packages/{workPackageId}/tasks")
    public ResponseEntity<TaskDto> createTask(Principal principal,@PathVariable long workPackageId, @RequestBody TaskDto taskDto) {
        TaskDto createdTask = leadEngineerService.createTask(principal.getName(), taskDto, workPackageId);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTask.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdTask);
    }


    @DeleteMapping("/work-packages/{workPackageId}/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(Principal principal, @PathVariable Long workPackageId, @PathVariable Long taskId) {
        leadEngineerService.deleteTask(principal.getName(), workPackageId, taskId);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("/work-packages/{workPackageId}/tasks/{taskId}")
    public ResponseEntity<TaskDto>  editTask(Principal principal, @PathVariable Long workPackageId, @RequestBody TaskDto taskDto, @PathVariable long taskId) {

        taskDto.setId(taskId);

        TaskDto updatedTask = leadEngineerService.editTask(principal.getName(), taskDto, workPackageId);

        return ResponseEntity.ok().body(updatedTask);
    }




}


