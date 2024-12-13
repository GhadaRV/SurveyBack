package com.ghada.survey.Controllers;

import com.ghada.survey.Entities.Survey;
import com.ghada.survey.Repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
@RequestMapping("/surveys")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @GetMapping
    public List<Survey> getAllSurveys() {
        List<Survey> surveys = surveyRepository.findAll();

        if (surveys.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No surveys found");
        }

        return surveys;
    }

    @GetMapping("/{id}")
    public Survey getSurvey(@PathVariable Long id) {
        return surveyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found"));
    }
    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyRepository.save(survey);
    }


}
