package com.interviewPlatform.services;

import com.interviewPlatform.dtos.request.InterviewerRegisterRequest;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

public interface InterviewerService {
    String registerInterviewer(InterviewerRegisterRequest dto);
    Map<String, String> uploadMyResume(String email, MultipartFile resumeFile);
    Map<String, String> uploadMyProfilePhoto(String email, MultipartFile photoFile);
}
