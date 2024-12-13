package com.ghada.survey.Controllers;
import com.ghada.survey.Entities.Question;
import com.ghada.survey.Entities.Survey;
import com.ghada.survey.Repositories.QuestionRepository;
import com.ghada.survey.Repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://survey-neon.vercel.app/")
@RequestMapping("/api/surveys")
public class QuestionController {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/{id}/questions")
    public ResponseEntity<String> addQuestionsToSurvey(@PathVariable Long id, @RequestBody List<Question> questions) {
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found"));
        if (questions.stream().anyMatch(q -> q.getQuestionText() == null || q.getQuestionText().isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All questions must have a valid questionText");
        }
        questions.forEach(question -> question.setSurvey(survey));
        questionRepository.saveAll(questions);
        return ResponseEntity.ok("Questions added to survey");
    }

}
