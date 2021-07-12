package com.czerwo.ftrot.roles.technicalProjectManager;

import com.czerwo.ftrot.models.dtos.WorkPackageSimplifiedDto;
import com.czerwo.ftrot.models.dtos.WorkPackageStatusDto;
import com.czerwo.ftrot.models.dtos.WorkPackageTasksDto;
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

    @GetMapping("/board/user-info")
    public ResponseEntity<UserInfoDto> getUserInfo(Principal principal){

        UserInfoDto userInfoDto = technicalProjectManagerService.getUserInfoByUsername(principal.getName());

        return ResponseEntity.ok().body(userInfoDto);
    }

    @GetMapping("/board/work-packages")
    public ResponseEntity<List<WorkPackageSimplifiedDto>> getTopFiveWorkPackagesWithClosestDeadline(Principal principal){

        List<WorkPackageSimplifiedDto> workPackageSimplifiedDtos =
                technicalProjectManagerService
                        .getTopFiveWorkPackagesWithClosestDeadline(principal.getName());

        return ResponseEntity.ok().body(workPackageSimplifiedDtos);
    }

    @GetMapping("/board/work-packages-status")
    public ResponseEntity<WorkPackageStatusDto> getWorkPackagesStatus(Principal principal){

        WorkPackageStatusDto workPackageStatusDto =
                technicalProjectManagerService
                        .getWorkPackagesStatus(principal.getName());

        return ResponseEntity.ok().body(workPackageStatusDto);
    }



    @GetMapping("/work-packages")
    public List<WorkPackageTasksDto> findAllWorkPackagesByOwnerUsername(Principal principal) {
        return technicalProjectManagerService
                .findAllWorkPackagesByOwnerUsername(principal.getName());
    }

    @GetMapping("/work-packages/lead-engineers")
    public List<LeadEngineerDto> getAllLeadEngineersFromYourTeam(Principal principal) {
        return technicalProjectManagerService
                .getAllLeadEngineersFromYourTeam(principal.getName());
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

    @DeleteMapping("/work-packages/{workPackageId}")
    public ResponseEntity<Void> deleteWorkPackage(Principal principal, @PathVariable Long workPackageId) {
        technicalProjectManagerService.deleteWorkPackage(principal.getName(), workPackageId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/work-packages/{workPackageId}")
    public ResponseEntity<WorkPackageDto>  editWorkPackage(Principal principal, @PathVariable Long workPackageId, @RequestBody WorkPackageDto workPackageDto) {

        //todo conflict check
        workPackageDto.setId(workPackageId);

        WorkPackageDto updatedWorkPackage = technicalProjectManagerService.editWorkPackage(principal.getName(), workPackageDto);

        return ResponseEntity.ok().body(updatedWorkPackage);
    }




}
