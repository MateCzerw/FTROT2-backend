package com.czerwo.reworktracking.ftrot.models.repositories;

import com.czerwo.reworktracking.ftrot.models.data.Day.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DayRepository extends JpaRepository<Day, Long> {

    List<Day> findAllByWeekId(Long weekId);




}
