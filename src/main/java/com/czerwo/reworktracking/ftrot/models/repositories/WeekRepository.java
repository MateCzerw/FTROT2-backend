package com.czerwo.reworktracking.ftrot.models.repositories;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.models.data.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeekRepository extends JpaRepository<Week, Long> {

    Optional<Week> findByWeekNumberAndYearNumberAndUser(int weekNumber, int yearNumber, ApplicationUser user);
}
