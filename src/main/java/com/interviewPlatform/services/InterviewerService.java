package com.interviewPlatform.services;

import com.interviewPlatform.dtos.request.InterviewerRegisterRequest;

public interface InterviewerService {
    String registerInterviewer(InterviewerRegisterRequest dto);
    java.util.Map<String, String> uploadMyResume(String email, org.springframework.web.multipart.MultipartFile resumeFile);
}
