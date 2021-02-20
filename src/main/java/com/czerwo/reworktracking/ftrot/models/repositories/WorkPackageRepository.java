package com.czerwo.reworktracking.ftrot.models.repositories;

import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkPackageRepository extends JpaRepository<WorkPackage, Long> {

//    @Query("SELECT e FROM WorkPackage e join fetch e.owner WHERE e.owner.username=?1")
//    List<WorkPackage> findAllByOwnerUsername(String username);



}
