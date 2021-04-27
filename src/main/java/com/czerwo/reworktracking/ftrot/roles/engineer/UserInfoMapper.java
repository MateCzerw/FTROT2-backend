package com.czerwo.reworktracking.ftrot.roles.engineer;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.models.data.Team;
import com.czerwo.reworktracking.ftrot.models.data.UserInfo;
import com.czerwo.reworktracking.ftrot.models.exceptions.Team.TeamNotFoundException;
import com.czerwo.reworktracking.ftrot.models.exceptions.User.TeamLeaderNotFoundException;
import com.czerwo.reworktracking.ftrot.models.exceptions.User.UserInfoNotFoundException;

import java.util.Optional;

class UserInfoMapper {

    static UserInfoDto toDto(Optional<ApplicationUser> user, Optional<ApplicationUser> teamLeader, double reworkRatio, int unFinishedTasks){
        UserInfoDto dto = new UserInfoDto();
        UserInfo userInfo = user
                .map(ApplicationUser::getUserInfo)
                .orElseThrow(() -> new UserInfoNotFoundException());
        dto.setName(userInfo.getName());
        dto.setSurname(userInfo.getSurname());
        dto.setRole(user.map(ApplicationUser::getAuthorities)
                .map(item -> item
                        .stream()
                        .findFirst()
                        .get()
                        .toString()
                        .substring(5))
                .orElseGet(() -> "Not assigned"));
        dto.setSupervisor(teamLeader.map(ApplicationUser::getUserInfo)
                .map((info) -> String.join(" " ,info.getName(), info.getSurname()))
                .orElseThrow(() -> new TeamLeaderNotFoundException()));
        dto.setReworkRatio(reworkRatio);
        dto.setJoinedAt(user
                .map(ApplicationUser::getUserInfo)
                .map(UserInfo::getJoinedAt)
                .orElseThrow(() -> new UserInfoNotFoundException()));
        dto.setUnfinishedTasks(unFinishedTasks);
        dto.setTeam(user.map(ApplicationUser::getTeam)
                .map(Team::getName)
                .orElseThrow(() -> new TeamNotFoundException()));

        dto.setPictureUrl(user.map(ApplicationUser::getUserInfo)
                .map(UserInfo::getPictureUrl)
                .orElseThrow(() -> new RuntimeException()));
        return dto;
    }
}
