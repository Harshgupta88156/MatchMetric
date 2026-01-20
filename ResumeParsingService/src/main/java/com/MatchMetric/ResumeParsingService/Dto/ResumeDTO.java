package com.MatchMetric.ResumeParsingService.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeDTO {
    private PersonalInfoDTO personalInfo;
    private List<EducationDTO> education;
    private List<ExperienceDTO> experience;
    private List<ProjectDTO> projects;
    private List<String> skills;
    private List<CertificationDTO> certifications;

    private String summary;

    private List<String> languages;
    private List<String> extracurricularActivities;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PersonalInfoDTO {
        private String name;
        private String email;
        private String phone;
        private String location;
        private String linkedIn;
        private String github;
        private String portfolio;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class EducationDTO {
        private String institution;
        private String degree;
        private String duration; // e.g., "2023 - 2027"
        private String score;    // e.g., "8.48 CGPA" or "91.8%"
        private String location;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ExperienceDTO {
        private String company;
        private String role;
        private String duration;
        private String location;
        private List<String> descriptions; // For bullet points
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProjectDTO {
        private String title;
        private String technologies; // e.g., "Spring Boot, React, Kafka"
        private List<String> highlights; // For bullet points of achievements
        private String link;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CertificationDTO {
        private String name;
        private String issuingOrganization;
        private String issueDate;
    }
}