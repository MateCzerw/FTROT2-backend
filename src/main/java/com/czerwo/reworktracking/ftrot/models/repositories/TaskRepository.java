package com.czerwo.reworktracking.ftrot.models.repositories;

import com.czerwo.reworktracking.ftrot.models.data.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("Select count(e) from Task e where e.assignedEngineer.id=?1 and e.status < 1")
    int countTaskByAssignedEngineerIdAndStatusIsNotStatusFinished(long id);

    // @Query("Select e from ApplicationUser e join fetch e.userInfo where e.id=?1")


    List<Task> findAllByAssignedEngineerId(Long id);

    //@Query("SELECT e FROM WorkPackage e join fetch e.tasks WHERE e.id=?1")
    List<Task> findAllByWorkPackageId(long id);

    List<Task> findAllByDayId(Long dayId);
}
