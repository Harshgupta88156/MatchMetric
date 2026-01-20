package com.MatchMetric.ResumeParsingService.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "resumes")
public class Resume {

    @Id
    private String id;

    private PersonalInfo personalInfo;
    private List<Experience> experience; // Added
    private List<Education> education;
    private List<Project> projects;

    // Categorized skills are better for AI matching
    private List<String> skills;

    private List<Certification> certifications;
    private List<String> languages;
    private List<String> awards;
}

@Data
class SkillCategory {
    private String category; // e.g., "Backend", "Tools", "Cloud"
    private List<String> values; // e.g., ["Java", "Spring Boot"]
}

@Data
class Certification {
    private String name;
    private String issuingOrganization;
    private String issueDate;
    private String credentialId;
}