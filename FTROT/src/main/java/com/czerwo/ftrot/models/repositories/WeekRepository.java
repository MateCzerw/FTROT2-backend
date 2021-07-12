package com.czerwo.ftrot.models.repositories;

import com.czerwo.ftrot.auth.ApplicationUser;
import com.czerwo.ftrot.models.data.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WeekRepository extends JpaRepository<Week, Long> {

    Optional<Week> findByWeekNumberAndYearNumberAndUser(int weekNumber, int yearNumber, ApplicationUser user);

    @Query("SELECT e FROM Week e " +
            "INNER JOIN e.days d " +
            "WHERE d.id=?1 ")
    Optional<Week> findWeekByDayId(long dayId);
}
