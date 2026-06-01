package com.interviewPlatform.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import com.interviewPlatform.entities.Institute;
import com.interviewPlatform.entities.User;

public interface InstituteRepository  extends JpaRepository<Institute,Long>{

    @Query("SELECT DISTINCT i FROM Institute i LEFT JOIN FETCH i.departments")
    List<Institute> findAllWithDepartments();

    Optional<Institute>  findByUser(User user);

    boolean existsByInstituteCode(String instituteCode);
    Optional<Institute> findByRegistrationToken(String token);
    Optional<Institute> findByUserEmail(String email);
    
}
