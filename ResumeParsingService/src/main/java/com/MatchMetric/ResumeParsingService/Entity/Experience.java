package com.MatchMetric.ResumeParsingService.Entity;

import lombok.Data;

import java.util.List;

@Data
public class Experience {
    private String company;
    private String role;
    private String location;
    private String startDate;
    private String endDate;
    private boolean isCurrentRole;
    private List<String> description; // Bullet points of responsibilities
    private List<String> technologiesUsed; // Tech stack for this specific role
}