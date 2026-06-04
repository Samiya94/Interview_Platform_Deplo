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
        if (userRepository.findByEmail("admin@platform.com").isPresent()) {
            log.info("Admin already exists. Skipping Data Seeder.");
            return;
        }

        log.info("Starting Data Seeder: Generating admin user...");

        // 1. Create Admin (if not exists)
        User adminUser = new User();
        adminUser.setEmail("admin@platform.com");
        adminUser.setPassword(passwordEncoder.encode("admin123"));
        adminUser.setRole(Role.ADMIN);
        adminUser.setStatus(Status.ACTIVE);
        userRepository.save(adminUser);

        log.info("Data Seeder completed successfully!");
    }
}
