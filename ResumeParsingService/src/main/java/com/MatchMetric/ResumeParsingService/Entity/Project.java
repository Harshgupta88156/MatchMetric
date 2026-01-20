package com.MatchMetric.ResumeParsingService.Entity;

import lombok.Data;

import java.util.List;

@Data
public class Project {
    private String title;
    private String description;
    private String projectLink; // Live URL or GitHub repo
    private List<String> technologies;
    private List<String> keyHighlights; // Specific achievements
    private String startDate;
    private String endDate;
}