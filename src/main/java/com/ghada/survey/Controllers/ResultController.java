package com.ghada.survey.Controllers;

import com.ghada.survey.DTO.QuestionDTO;
import com.ghada.survey.DTO.ResponseDTO;
import com.ghada.survey.DTO.SurveyDTO;
import com.ghada.survey.Entities.Question;
import com.ghada.survey.Entities.Response;
import com.ghada.survey.Entities.Survey;
import com.ghada.survey.Repositories.QuestionRepository;
import com.ghada.survey.Repositories.ResponseRepository;
import com.ghada.survey.Repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "https://survey-neon.vercel.app/")
@RequestMapping("/api/surveys")
public class ResultController {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private QuestionRepository questionRepository;



    @PostMapping("/{surveyID}/{questionID}/rate")
    public ResponseEntity<String> submitResponse(@PathVariable Long surveyID,@PathVariable Long questionID,  @RequestBody int rating) {
        Survey survey = surveyRepository.findById(surveyID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found"));
        Question question = questionRepository.findById(questionID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found"));
        if (!survey.getQuestions().contains(question)) {
            throw new RuntimeException("Question does not belong to the specified survey.");
        }
        if (rating < 0 || rating > 5) {
            throw new RuntimeException("Rating must be between 0 and 5.");
        }

        Response newRating = new Response();
        newRating.setRating(rating);
        newRating.setQuestion(question);
        responseRepository.save(newRating);

        return ResponseEntity.ok("Rating sumbmitted successfully.");
    }

    @PostMapping("/{surveyID}/rate-all")
    public ResponseEntity<String> rateAll(@PathVariable Long surveyID, @RequestBody List<Integer> ratings) {

        Survey survey = surveyRepository.findById(surveyID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found"));

        List<Question> questions = survey.getQuestions();
        for (int i = 0; i < ratings.size(); i++) {
            Question question = questions.get(i);
            int rating = ratings.get(i);

            if (rating < 0 || rating > 5) {
                throw new RuntimeException("Rating must be between 0 and 5.");
            }

            Response response = new Response();
            response.setRating(rating);
            response.setQuestion(question);
            responseRepository.save(response);
        }

        return ResponseEntity.ok("All ratings submitted successfully.");
    }


    @GetMapping("/{id}/results")
    public ResponseEntity<SurveyDTO> getSurveyResults(@PathVariable Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        SurveyDTO surveyDTO = new SurveyDTO();
        surveyDTO.setId(survey.getId());
        surveyDTO.setName(survey.getName());


        List<QuestionDTO> questionResults = survey.getQuestions().stream()
                .map(question -> {
                    QuestionDTO questionResultsDTO = new QuestionDTO();
                    questionResultsDTO.setId(question.getId());
                    questionResultsDTO.setQuestionText(question.getQuestionText());
                    List<ResponseDTO> ratings = responseRepository.findByQuestionId(question.getId()).stream()
                            .map(response -> {
                                ResponseDTO responseDTO = new ResponseDTO();
                                responseDTO.setRating(response.getRating());
                                return responseDTO;
                            })
                            .collect(Collectors.toList());

                    questionResultsDTO.setRatings(ratings);
                    return questionResultsDTO;
                })
                .collect(Collectors.toList());

        surveyDTO.setQuestions(questionResults);

        return ResponseEntity.ok(surveyDTO);
    }
}
