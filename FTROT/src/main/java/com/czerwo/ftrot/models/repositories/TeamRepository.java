package com.czerwo.ftrot.models.repositories;

import com.czerwo.ftrot.models.data.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {


    Optional<Team> findByName(String teamName);

    Optional<Team> findByTeamLeaderUsername(String username);


}
