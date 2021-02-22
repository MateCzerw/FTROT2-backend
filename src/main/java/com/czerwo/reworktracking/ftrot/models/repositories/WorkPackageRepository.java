package com.czerwo.reworktracking.ftrot.models.repositories;

import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WorkPackageRepository extends JpaRepository<WorkPackage, Long> {

    @Query("SELECT DISTINCT e FROM WorkPackage e " +
            "LEFT JOIN FETCH e.tasks " +
            "WHERE e.assignedTechnicalProjectManager.username=?1")
    List<WorkPackage> findAllByOwnerUsernameWithTasks(String username);

    @Query("SELECT DISTINCT e FROM WorkPackage e " +
            "LEFT JOIN FETCH e.tasks " +
            "WHERE e.assignedLeadEngineer.username=?1")
    List<WorkPackage> findAllWorkPackagesByAssignedLeadEngineer(String username);


    @Query("SELECT DISTINCT e FROM WorkPackage e " +
            "LEFT JOIN FETCH e.tasks " +
            "LEFT JOIN FETCH e.assignedLeadEngineer " +
            "WHERE e.id=?1")
    Optional<WorkPackage> findByIdWithAssignedLeadEngineerAndTasks(Long workPackageId);

    @Query("SELECT e FROM WorkPackage e " +
            "JOIN FETCH e.assignedLeadEngineer " +
            "WHERE e.id=?1")
    Optional<WorkPackage> findByIdWithAssignedLeadEngineer(long workPackageId);
}
