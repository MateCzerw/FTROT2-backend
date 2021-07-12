package com.czerwo.ftrot.roles.engineer;

import com.czerwo.ftrot.models.dtos.WeekDto;
import com.czerwo.ftrot.models.DataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/engineer")
public class EngineerController {

    private final EngineerService engineerService;


    public EngineerController(EngineerService engineerService, DataService dataService) {
        this.engineerService = engineerService;

    }



    @GetMapping("/board/user-info")
    public ResponseEntity<UserInfoDto> getUserInfo(Principal principal){

        UserInfoDto userInfoDto = engineerService.getUserInfoByUsername(principal.getName());

        return ResponseEntity.ok().body(userInfoDto);
    }

    @GetMapping("/board/tasks")
    public ResponseEntity<List<TaskSimplifyDto>> getTasksForDay(Principal principal){



        List<TaskSimplifyDto> tasksForDay = engineerService.getTasksForDay(principal.getName());


        return ResponseEntity.ok().body(tasksForDay);
    }

    @GetMapping("/board/graph-details")
    public ResponseEntity<Integer> getTotalDurationOfAssignedTasksInCurrentWeek(Principal principal){

        Integer assignedTasksDuration = engineerService
                .getTotalDurationOfAssignedTasksInCurrentWeek(
                        principal.getName()
                        );

        return ResponseEntity.ok().body(assignedTasksDuration);
    }

    @GetMapping("/tasks")
    public ResponseEntity<WeekDto> getCurrentUserWeekWithTasks(Principal principal){

        WeekDto userWeekWithTasks = engineerService.getCurrentUserWeekWithTasks(principal.getName());

        return ResponseEntity.ok().body(userWeekWithTasks);
    }

    @PatchMapping("/tasks/{taskId}")
    public ResponseEntity<Void> changeTaskStatus(Principal principal, @PathVariable long taskId, @RequestParam double status){

        engineerService.editTaskStatus(principal.getName(), status, taskId);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/tasks/next-week")
    public ResponseEntity<WeekDto> getNextUserWeekWithTasks(Principal principal, @RequestParam int weekNumber, @RequestParam int yearNumber){

        WeekDto nextUserWeekWithTasks = engineerService.getNextUserWeekWithTasks(principal.getName(), weekNumber, yearNumber);

        return ResponseEntity.ok().body(nextUserWeekWithTasks);
    }

    @GetMapping("/tasks/previous-week")
    public ResponseEntity<WeekDto> getPreviousUserWeekWithTasks(Principal principal, @RequestParam int weekNumber, @RequestParam int yearNumber){

        WeekDto previousUserWeekWithTasks = engineerService.getPreviousUserWeekWithTasks(principal.getName(), weekNumber, yearNumber);

        return ResponseEntity.ok().body(previousUserWeekWithTasks);

    }


}
