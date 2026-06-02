package com.interviewPlatform.config;

import com.interviewPlatform.entities.*;
import com.interviewPlatform.enums.Role;
import com.interviewPlatform.enums.Status;
import com.interviewPlatform.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final InstituteRepository instituteRepository;
    private final DepartmentRepository departmentRepository;
    private final MentorRepository mentorRepository;
    private final StudentRepository studentRepository;
    private final InterviewerRepository interviewerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            log.info("Database is already populated. Skipping Data Seeder.");
            return;
        }

        log.info("Starting Data Seeder: Generating 200 students and platform data...");
        String defaultPassword = passwordEncoder.encode("password123");

        // 1. Create Admin
        User adminUser = new User();
        adminUser.setEmail("admin@platform.com");
        adminUser.setPassword(passwordEncoder.encode("admin123"));
        adminUser.setRole(Role.ADMIN);
        adminUser.setStatus(Status.ACTIVE);
        userRepository.save(adminUser);

        // 2. Create Institute
        User instUser = new User();
        instUser.setEmail("institute@example.com");
        instUser.setPassword(defaultPassword);
        instUser.setRole(Role.INSTITUTE);
        instUser.setStatus(Status.ACTIVE);
        userRepository.save(instUser);

        Institute institute = new Institute();
        institute.setUser(instUser);
        institute.setInstituteName("Indian Institute of Technology, Bombay");
        institute.setUniversity("IIT System");
        institute.setInstituteCode("IITB001");
        institute.setAddress("Main Gate Rd, IIT Area, Powai");
        institute.setCity("Mumbai");
        institute.setState("Maharashtra");
        institute.setAdminName("Dr. Subhasis Chaudhuri");
        institute.setContactNumber("02225722545");
        institute.setStudentStrength(200);
        instituteRepository.save(institute);

        // 3. Create Departments
        Department csDept = new Department();
        csDept.setInstitute(institute);
        csDept.setName("Computer Science and Engineering");
        departmentRepository.save(csDept);

        Department itDept = new Department();
        itDept.setInstitute(institute);
        itDept.setName("Information Technology");
        departmentRepository.save(itDept);

        // 4. Create Mentors
        User mentorUser1 = new User();
        mentorUser1.setEmail("mentor.cs@example.com");
        mentorUser1.setPassword(defaultPassword);
        mentorUser1.setRole(Role.MENTOR);
        mentorUser1.setStatus(Status.ACTIVE);
        userRepository.save(mentorUser1);

        Mentor mentor1 = new Mentor();
        mentor1.setUser(mentorUser1);
        mentor1.setInstitute(institute);
        mentor1.setDepartment(csDept);
        mentor1.setFirstName("Rajesh");
        mentor1.setLastName("Kumar");
        mentor1.setDesignation("HOD");
        mentor1.setPhone("9876543210");
        mentorRepository.save(mentor1);

        // 5. Create Interviewers
        String[] intNames = {"Amit Sharma", "Priya Patel", "Vikram Singh", "Neha Gupta", "Ravi Verma"};
        String[] domains = {"Software Engineering", "Data Science", "Cloud Computing", "Cyber Security", "AI/ML"};
        for (int i = 0; i < intNames.length; i++) {
            User iUser = new User();
            iUser.setEmail("interviewer" + (i + 1) + "@example.com");
            iUser.setPassword(defaultPassword);
            iUser.setRole(Role.INTERVIEWER);
            iUser.setStatus(Status.ACTIVE);
            userRepository.save(iUser);

            Interviewer interviewer = new Interviewer();
            interviewer.setUser(iUser);
            interviewer.setFullName(intNames[i]);
            interviewer.setCompany("TCS / Infosys / Wipro");
            interviewer.setExperience("5+ Years");
            interviewer.setDomain(domains[i]);
            interviewer.setJobTitle("Senior Engineer");
            interviewer.setSkills(List.of("Java", "Python", "System Design"));
            interviewer.setAverageRating(4.5 + (Math.random() * 0.5));
            interviewer.setInterviewsConducted((int) (Math.random() * 20));
            interviewerRepository.save(interviewer);
        }

        // 6. Generate 200 Indian Students
        String[] firstNames = {"Aarav", "Aditi", "Rohan", "Ananya", "Ishaan", "Diya", "Karan", "Kavya", "Aryan", "Meera",
                "Arjun", "Neha", "Rahul", "Pooja", "Siddharth", "Riya", "Varun", "Shruti", "Yash", "Tanvi", "Aditya", "Sneha"};
        String[] lastNames = {"Sharma", "Patel", "Reddy", "Singh", "Gupta", "Desai", "Joshi", "Kumar", "Iyer", "Nair",
                "Das", "Chauhan", "Verma", "Yadav", "Bhat", "Shah", "Mehta", "Malhotra", "Pandey", "Rao"};

        Random random = new Random();
        for (int i = 1; i <= 200; i++) {
            String fName = firstNames[random.nextInt(firstNames.length)];
            String lName = lastNames[random.nextInt(lastNames.length)];
            String email = fName.toLowerCase() + "." + lName.toLowerCase() + i + "@student.iitb.edu.in";

            User sUser = new User();
            sUser.setEmail(email);
            sUser.setPassword(defaultPassword);
            sUser.setRole(Role.STUDENT);
            sUser.setStatus(Status.ACTIVE);
            userRepository.save(sUser);

            Student student = new Student();
            student.setUser(sUser);
            student.setInstitute(institute);
            student.setDepartment(i % 2 == 0 ? csDept : itDept);
            student.setFirstName(fName);
            student.setLastName(lName);
            student.setStudentClass(i % 2 == 0 ? "B.Tech Final Year" : "M.Tech 1st Year");
            student.setPhone("9" + (100000000 + random.nextInt(900000000)));
            student.setCgpa(7.0 + (random.nextDouble() * 3.0));
            studentRepository.save(student);
        }

        log.info("Data Seeder completed successfully!");
    }
}
