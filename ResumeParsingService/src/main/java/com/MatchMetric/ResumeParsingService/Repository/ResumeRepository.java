package com.MatchMetric.ResumeParsingService.Repository;

import com.MatchMetric.ResumeParsingService.Entity.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResumeRepository extends MongoRepository<Resume,String> {
}
