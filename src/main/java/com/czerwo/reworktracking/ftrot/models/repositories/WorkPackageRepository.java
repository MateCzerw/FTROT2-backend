package com.czerwo.reworktracking.ftrot.models.repositories;

import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkPackageRepository extends JpaRepository<WorkPackage, Long> {

    @Query("SELECT DISTINCT e FROM WorkPackage e " +
            "JOIN FETCH e.tasks " +
            "WHERE e.assignedTechnicalProjectManager.username=?1")
    List<WorkPackage> findAllByOwnerUsernameWithTasks(String username);

    @Query("SELECT DISTINCT e FROM WorkPackage e " +
            "JOIN FETCH e.tasks " +
            "WHERE e.assignedLeadEngineer.username=?1")
    List<WorkPackage> findAllWorkPackagesByAssignedLeadEngineer(String username);



}
