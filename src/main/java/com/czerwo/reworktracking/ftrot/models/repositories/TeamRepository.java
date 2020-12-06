package com.czerwo.reworktracking.ftrot.models.repositories;

import com.czerwo.reworktracking.ftrot.models.data.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
