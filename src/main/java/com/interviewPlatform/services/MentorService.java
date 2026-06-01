package com.interviewPlatform.services;

import java.util.List;

import com.interviewPlatform.dtos.request.MentorRegisterRequest;
import com.interviewPlatform.dtos.response.MentorResponse;

public interface MentorService {
    void registerMentor(MentorRegisterRequest request);
    List<MentorResponse> getMentorsByInstitute(Long instituteId);
    void approveInterviewer(Long mentorId, Long interviewerId);
    void deleteInterviewer(Long mentorId, Long interviewerId);
    java.util.Map<String, String> uploadMyProfilePhoto(String email, org.springframework.web.multipart.MultipartFile photoFile);
}
