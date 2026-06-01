package com.interviewPlatform.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interviewPlatform.entities.Department;
import com.interviewPlatform.entities.Mentor;
import com.interviewPlatform.entities.User;

public interface MentorRepository extends JpaRepository<Mentor,Long> {
    boolean existsByDepartment(Department department);
    Optional<Mentor> findByDepartmentId(Long departmentId);
    List<Mentor> findByInstituteId(Long instituteID);
    Optional<Mentor> findByUserEmail(String email);
    Optional<Mentor> findByUser(User user);
}
