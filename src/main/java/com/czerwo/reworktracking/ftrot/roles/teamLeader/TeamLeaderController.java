package com.czerwo.reworktracking.ftrot.roles.teamLeader;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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




}
