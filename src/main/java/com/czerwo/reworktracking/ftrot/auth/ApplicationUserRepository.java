package com.czerwo.reworktracking.ftrot.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {


    Optional<ApplicationUser> findByUsername(String username);


    @Query("SELECT e FROM ApplicationUser e " +
            "JOIN FETCH e.userInfo " +
            "JOIN FETCH e.team " +
            "WHERE e.username=?1")
    Optional<ApplicationUser> findByUsernameWithTeamAndUserInfo(String username);

    @Query("SELECT e FROM ApplicationUser e " +
            "WHERE e.team.teamLeader.username=?1 " +
            "AND (e.applicationUseRole='ENGINEER' OR e.applicationUseRole='LEAD_ENGINEER')")
    List<ApplicationUser> findEngineersAndLeadEngineersFromTeamByTeamLeaderUsername(String leaderUsername);



}
