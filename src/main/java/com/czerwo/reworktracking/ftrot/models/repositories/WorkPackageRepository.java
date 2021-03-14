package com.czerwo.reworktracking.ftrot.models.repositories;

import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    List<WorkPackage> findAllWorkPackagesByAssignedLeadEngineerWithTasks(String username);


    @Query("SELECT DISTINCT e FROM WorkPackage e " +
            "LEFT JOIN FETCH e.tasks " +
            "LEFT JOIN FETCH e.assignedLeadEngineer " +
            "WHERE e.id=?1")
    Optional<WorkPackage> findByIdWithAssignedLeadEngineerAndTasks(Long workPackageId);

    @Query("SELECT e FROM WorkPackage e " +
            "JOIN FETCH e.assignedLeadEngineer " +
            "WHERE e.id=?1")
    Optional<WorkPackage> findByIdWithAssignedLeadEngineer(long workPackageId);

    @Query("SELECT e FROM WorkPackage e " +
            "WHERE e.assignedLeadEngineer.username=?1 " +
            "AND e.isFinished=false " +
            "ORDER BY e.deadline ASC")
    List<WorkPackage> findTop5ByAssignedLeadEngineerUsernameAndOrderByDeadlineAsc(String username, Pageable topFive);

    @Query("SELECT e FROM WorkPackage e " +
            "WHERE e.assignedTechnicalProjectManager.username=?1 " +
            "AND e.isFinished=false " +
            "ORDER BY e.deadline ASC")
    List<WorkPackage> findTop5ByAssignedTechnicalProjectManagerUsernameOrderByDeadlineAsc(String username, Pageable topFive);

    @Query("SELECT e FROM WorkPackage e " +
            "WHERE e.team.id=?1 " +
            "AND e.isFinished=false " +
            "ORDER BY e.deadline ASC")
    List<WorkPackage> findTop5ByTeamIdOrderByDeadlineAsc(Long id, Pageable topFive);


    @Query("SELECT COUNT(e) " +
            "FROM WorkPackage e " +
            "WHERE e.assignedTechnicalProjectManager.username=?1 " +
            "AND e.isFinished = true")
    int countWorkPackagesWhereStatusIsFinishedAndUsernameIsOwner(String username);

    @Query("SELECT COUNT(e) " +
            "FROM WorkPackage e " +
            "WHERE e.assignedTechnicalProjectManager.username=?1 " +
            "AND e.isFinished = false")
    int countWorkPackagesWhereStatusIsNotFinishedAndUsernameIsOwner(String username);

}
