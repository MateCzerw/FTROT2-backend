package com.czerwo.reworktracking.ftrot.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {


    Optional<ApplicationUser> findByUsername(String username);


    @Query("Select e from ApplicationUser e " +
            "join fetch e.userInfo " +
            "join fetch e.team " +
            "where e.username=?1")
    Optional<ApplicationUser> findByUsernameWithTeamAndUserInfo(String username);



}
