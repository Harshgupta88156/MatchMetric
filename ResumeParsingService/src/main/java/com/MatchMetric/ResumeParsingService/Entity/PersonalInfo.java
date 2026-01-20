package com.MatchMetric.ResumeParsingService.Entity;

import lombok.Data;

@Data
public class PersonalInfo {
    private String name;
    private String email;
    private String phone;
    private String location; // City, State/Country
    private String linkedinUrl;
    private String githubUrl;
    private String portfolioUrl;
    private String summary; // Professional bio/headline
}