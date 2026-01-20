package com.MatchMetric.ResumeParsingService.Service;

import com.MatchMetric.ResumeParsingService.Dto.ResumeDTO;
import com.MatchMetric.ResumeParsingService.Entity.Resume;
import com.MatchMetric.ResumeParsingService.Repository.ResumeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final GeminiParserService geminiParserService;
    private ModelMapper mapper;

    public Resume resumeSave(String text){
        ResumeDTO resumeDTO =  geminiParserService.parseResume(text);
        Resume resumeEntity = mapper.map(resumeDTO, Resume.class);
        return resumeRepository.save(resumeEntity);
    }





}
