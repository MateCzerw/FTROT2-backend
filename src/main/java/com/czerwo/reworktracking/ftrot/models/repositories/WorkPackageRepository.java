package com.czerwo.reworktracking.ftrot.models.repositories;

import com.czerwo.reworktracking.ftrot.models.data.Task.Task;
import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WorkPackageRepository extends JpaRepository<WorkPackage, Long> {

//    @Query("SELECT e FROM WorkPackage e join fetch e.owner WHERE e.owner.username=?1")
//    List<WorkPackage> findAllByOwnerUsername(String username);



}
