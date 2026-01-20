package com.MatchMetric.ResumeParsingService.Entity;

import lombok.Data;

@Data
public class Education {
    private String institution;
    private String location;
    private String degree;
    private String fieldOfStudy; // e.g., "Computer Science"
    private String startDate;
    private String endDate;
    private boolean isCurrentlyStudying;
    private String score; // GPA or Percentage
}
