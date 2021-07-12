package com.czerwo.ftrot.models.repositories;

import com.czerwo.ftrot.auth.ApplicationUser;
import com.czerwo.ftrot.models.data.Day.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DayRepository extends JpaRepository<Day, Long> {

    List<Day> findAllByWeekId(Long weekId);


    @Query("SELECT e FROM Day e " +
            "WHERE e.week.user=?1 " +
             "AND e.date=?2"
            )
    Optional<Day> findByEngineerAndDate(ApplicationUser userByUsername, LocalDate date);
}
