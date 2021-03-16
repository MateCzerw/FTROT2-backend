package com.czerwo.reworktracking.ftrot.roles.teamLeader;

import com.czerwo.reworktracking.ftrot.models.dtos.WeekDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageSimplifiedDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/team-leader")
public class TeamLeaderController {

    private final TeamLeaderService teamLeaderService;

    public TeamLeaderController(TeamLeaderService teamLeaderService) {
        this.teamLeaderService = teamLeaderService;
    }

    @GetMapping("/board/user-info")
    public ResponseEntity<UserInfoDto> getUserInfo(Principal principal){


        UserInfoDto userInfoDto = teamLeaderService.getUserInfoByUsername(principal.getName());

        return ResponseEntity.ok().body(userInfoDto);
    }

    @GetMapping("/board/work-packages")
    public ResponseEntity<List<WorkPackageSimplifiedDto>> getTopFiveWorkPackagesWithClosestDeadline(Principal principal){

        List<WorkPackageSimplifiedDto> workPackageSimplifiedDtos =
                teamLeaderService
                        .getTopFiveWorkPackagesWithClosestDeadline(principal.getName());

        return ResponseEntity.ok().body(workPackageSimplifiedDtos);
    }

    @GetMapping("/board/assigned-work-hours")
    public ResponseEntity<Integer> statusOfAssignedWorkHoursForCurrentWeek(Principal principal){

        int assignedHours =
                teamLeaderService
                        .getAssignedHoursForCurrentWeek(principal.getName());

        return ResponseEntity.ok().body(assignedHours);
    }

    @GetMapping("/assign-tasks/data")
    public ResponseEntity<AssignTasksPanelDto> getDataForAssignTasksPanel(Principal principal){

        AssignTasksPanelDto assignTasksPanelDto =
                teamLeaderService
                        .getDataForAssignTasksPanelForCurrentWeek(principal.getName());

        return ResponseEntity.ok().body(assignTasksPanelDto);
    }

    @GetMapping("/assign-tasks/weeks")
    public ResponseEntity<WeekDto> getUserWeekWithTasks(Principal principal, @RequestParam int weekNumber, @RequestParam int yearNumber, @RequestParam  long engineerId){

        WeekDto weekDto =
                teamLeaderService
                        .getWeekWithTasksForEngineerByEngineerId(principal.getName(), weekNumber, yearNumber, engineerId);

        return ResponseEntity.ok().body(weekDto);
    }

    @PatchMapping("/assign-tasks/tasks/{taskId}")
    public ResponseEntity<Void> modifyTaskAssignment(Principal principal,
                                                              @PathVariable long taskId,
                                                              @RequestBody AssignTaskDto assignTaskDto){

                teamLeaderService
                        .modifyTaskAssignment(principal.getName(), assignTaskDto, taskId);

        return ResponseEntity.noContent().build();
    }



}
