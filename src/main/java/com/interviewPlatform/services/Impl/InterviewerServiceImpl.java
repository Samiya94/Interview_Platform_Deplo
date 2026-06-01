package com.interviewPlatform.services.Impl;

import org.springframework.stereotype.Service;

import com.interviewPlatform.dtos.request.InterviewerRegisterRequest;
import com.interviewPlatform.entities.Interviewer;
import com.interviewPlatform.repositories.InterviewerRepository;
import com.interviewPlatform.repositories.UserRepository;
import com.interviewPlatform.services.AuthService;
import com.interviewPlatform.services.InterviewerService;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterviewerServiceImpl implements InterviewerService {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final InterviewerRepository interviewerRepository;

    @Value("${file.upload.dir:uploads/}")
    private String uploadDir;

    @Override
    public String registerInterviewer(InterviewerRegisterRequest dto) {
        authService.registerInterviewer(dto);
        return "Interviewer Registered Successfully";
    }

    @Override
    public Map<String, String> uploadMyResume(String email, MultipartFile resumeFile) {
        if (resumeFile == null || resumeFile.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Interviewer interviewer = interviewerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Interviewer profile not found"));

        try {
            String resumeFileName = System.currentTimeMillis() + "_resume_" +
                    resumeFile.getOriginalFilename();

            String dir = uploadDir != null && !uploadDir.isBlank() ? uploadDir : "uploads/";
            if (!dir.endsWith("/")) dir += "/";
            Path resumePath = Paths.get(dir + "resumes/" + resumeFileName);
            Files.createDirectories(resumePath.getParent());
            Files.write(resumePath, resumeFile.getBytes());

            interviewer.setResumeUrl("/uploads/resumes/" + resumeFileName);
            interviewerRepository.save(interviewer);

            Map<String, String> res = new HashMap<>();
            res.put("resumeUrl", "/uploads/resumes/" + resumeFileName);
            res.put("resumeFileName", resumeFileName);
            return res;
        } catch (Exception e) {
            throw new RuntimeException("Resume upload failed", e);
        }
    }
}
