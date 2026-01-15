package com.MatchMetric.ResumeParsingService.Controller;


import com.MatchMetric.ResumeParsingService.Dto.ResumeDTO;
import com.MatchMetric.ResumeParsingService.Service.GeminiParserService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class ResumeController {

    @Autowired
    private GeminiParserService geminiParserService;

    @PostMapping("/extract")
    public ResponseEntity<?> extractText(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty() || !file.getContentType().equals("application/pdf")) {
            return ResponseEntity.badRequest().body("Please upload a valid PDF file.");
        }

        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);


            System.out.println(text);

            // Send raw text to Gemini for structuring
            ResumeDTO structuredResume = geminiParserService.parseResume(text);

            if (structuredResume == null) {
                return ResponseEntity.internalServerError().body("Failed to parse resume structure.");
            }

            return ResponseEntity.ok(structuredResume);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error processing PDF: " + e.getMessage());
        }
    }
}