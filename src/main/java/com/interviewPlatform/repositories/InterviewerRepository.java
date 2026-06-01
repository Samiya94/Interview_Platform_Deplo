package com.interviewPlatform.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.interviewPlatform.entities.Interviewer;
import com.interviewPlatform.entities.User;
import com.interviewPlatform.enums.Status;

public interface InterviewerRepository extends JpaRepository<Interviewer,Long>{
    
    @Query("SELECT DISTINCT i FROM Interviewer i LEFT JOIN FETCH i.skills")
    List<Interviewer> findAllWithSkills();

    Optional<Interviewer> findByUser(User user);
    Optional<Interviewer> findByUserEmail(String email);
    List<Interviewer> findByUserStatus(Status status); 
}
