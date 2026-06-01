package com.interviewPlatform.dtos.request;

import java.util.List;

public record StudentProfileUpdateRequestDTO(
        String studentClass,
        Double cgpa,
        List<String> skills,
        String projects
) {
}
