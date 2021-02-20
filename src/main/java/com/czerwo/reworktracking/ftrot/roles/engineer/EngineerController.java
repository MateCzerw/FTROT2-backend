package com.czerwo.reworktracking.ftrot.roles.engineer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/engineer")
public class EngineerController {

    EngineerService engineerService;

    public EngineerController(EngineerService engineerService) {
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

    @GetMapping("/tasks")
    public ResponseEntity<WeekDto> getUserWeekWithTasks(Principal principal, @RequestParam int weekNumber, @RequestParam int yearNumber){

        WeekDto userWeekWithTasks = engineerService.getUserWeekWithTasks(principal.getName(), weekNumber, yearNumber);

        return ResponseEntity.ok().body(userWeekWithTasks);
    }

    // LIST OF TASKS TO DO

    // TO SET THAT TASK IS DONE

    // TO SET THAT TASK IS NOT DONE



}
