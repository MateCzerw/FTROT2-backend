package com.czerwo.reworktracking.ftrot.models.repositories;

import com.czerwo.reworktracking.ftrot.models.data.Task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    //@Query("SELECT e FROM WorkPackage e join fetch e.tasks WHERE e.id=?1")
    List<Task> findAllByWorkPackageId(long id);
}
