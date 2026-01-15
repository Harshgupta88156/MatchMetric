package com.MatchMetric.ResumeParsingService.Service;



import com.MatchMetric.ResumeParsingService.Dto.ResumeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.List;

@Service
public class GeminiParserService {

    @Value("${gemini.api.key}")
    private String apiKey;


    private static final String GEMINI_API_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResumeDTO parseResume(String rawText) {
        String url = GEMINI_API_URL + apiKey;

        // 1. Strict Prompt for 2026 Gemini 3
        String prompt = "Act as a resume parser. Extract data from the text below into a valid JSON object. " +
                "Use this EXACT structure: { \"personalInfo\": { \"name\":\"\", \"email\":\"\", \"phone\":\"\", \"location\":\"\", \"linkedIn\":\"\", \"github\":\"\" }, " +
                "\"education\": [{ \"institution\":\"\", \"degree\":\"\", \"duration\":\"\", \"score\":\"\" }], " +
                "\"experience\": [{ \"company\":\"\", \"role\":\"\", \"duration\":\"\", \"location\":\"\", \"descriptions\":[] }], " +
                "\"projects\": [{ \"title\":\"\", \"technologies\":\"\", \"highlights\":[] }], " +
                "\"skills\": [], \"certifications\": [{ \"name\":\"\", \"issuingOrganization\":\"\" }] }. " +
                "If a section is missing, leave it as null. Output ONLY the raw JSON string.\n\n" +
                "Resume Text:\n" + rawText;

        // 2. Build Request Body
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(Map.of("text", prompt)))
                )
        );

        RestTemplate restTemplate = new RestTemplate();
        try {
            // 3. API Call
            Map<String, Object> response = restTemplate.postForObject(url, requestBody, Map.class);

            // 4. Extract Text from response path: candidates[0].content.parts[0].text
            List candidates = (List) response.get("candidates");
            Map firstCandidate = (Map) candidates.get(0);
            Map content = (Map) firstCandidate.get("content");
            List parts = (List) content.get("parts");
            String aiJson = (String) ((Map) parts.get(0)).get("text");

            // 5. Robust JSON Cleaning (Extracts only the {} part)
            String cleanedJson = aiJson.substring(aiJson.indexOf("{"), aiJson.lastIndexOf("}") + 1);

            System.out.println("Cleaned JSON: " + cleanedJson);

            // 6. Map to DTO
            return objectMapper.readValue(cleanedJson, ResumeDTO.class);

        } catch (Exception e) {
            System.err.println("Error parsing resume with Gemini: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}