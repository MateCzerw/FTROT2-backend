package com.czerwo.reworktracking.ftrot.roles.technicalProjectManager;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.models.data.Team;
import com.czerwo.reworktracking.ftrot.models.data.UserInfo;


import java.util.Optional;

class UserInfoMapper {


    static UserInfoDto toDto(Optional<ApplicationUser> user){
        UserInfoDto dto = new UserInfoDto();
        UserInfo userInfo = user.map(ApplicationUser::getUserInfo).orElseThrow(() -> new RuntimeException());
        dto.setName(userInfo.getName());
        dto.setSurname(userInfo.getSurname());
        dto.setRole(user.map(ApplicationUser::getAuthorities).map(item -> item.stream().findFirst().get().toString().substring(5)).orElseGet(() -> "Not assigned"));
        dto.setSupervisor(user
                .map(ApplicationUser::getTeam)
                .map(Team::getTeamLeader)
                .map(ApplicationUser::getUserInfo)
                .map(UserInfo::getSurname)
                .orElseThrow(() -> new RuntimeException()));
        //todo
        dto.setUnfinishedWorkPackages(60);
        dto.setJoinedAt(user.map(ApplicationUser::getUserInfo).map(UserInfo::getJoinedAt).orElseThrow(() -> new RuntimeException()));
        //todo
        dto.setFinishedWorkPackages(32);
        dto.setTeam(user.map(ApplicationUser::getTeam).map(Team::getName).orElseThrow(() -> new RuntimeException()));


        return dto;
    }
}
