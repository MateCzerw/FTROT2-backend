package com.czerwo.reworktracking.ftrot.models.repositories;

import com.czerwo.reworktracking.ftrot.models.data.Day.Day;
import com.czerwo.reworktracking.ftrot.models.data.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT COUNT(e) " +
            "FROM Task e " +
            "WHERE e.assignedEngineer.id=?1 " +
            "AND e.status < 1")
    int countTaskByAssignedEngineerIdAndStatusIsNotStatusFinished(long id);

    @Query("Select coalesce(sum(e.duration), 0) " +
            "from Task e " +
            "where e.assignedEngineer.id=?1 " +
            "and e.day.week.weekNumber =?2 " +
            "and e.day.week.yearNumber =?3")
    int sumTasksByAssignerEngineerIdAndWeekAndYear(long id, int weekNumber, int yearNumber);

    @Query("SELECT e FROM Task e " +
            "WHERE e.assignedEngineer.id=?1 " +
            "AND e.day IS NULL")
    List<Task> findTasksFromBacklogByEngineerId(long id);

    @Query("SELECT e FROM Task e " +
            "WHERE e.assignedEngineer IS NULL " +
            "AND e.workPackage.team.id =?1")
    List<Task> findUnassignedTasksByTeamId(Long teamId);

    @Query("SELECT e FROM Task e " +
            "WHERE e.assignedEngineer.id=?1 " +
            "AND e.workPackage.team.id =?1")
    List<Task> findAllByAssignedEngineerIdAndDate(Long id, LocalDate date);


    List<Task> findAllByDayId(Long dayId);

    @Query("SELECT e FROM Task e " +
            "WHERE e.assignedEngineer.id=?1 " +
            "AND e.day =?2")
    List<Task> findAllByAssignedEngineerIdAndDay(long assignedEngineerId, Day dayByDate);
}
