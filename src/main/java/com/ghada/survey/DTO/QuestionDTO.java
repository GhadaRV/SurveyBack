package com.ghada.survey.DTO;

import java.util.List;

public class QuestionDTO {


    private Long id;
    private String questionText;
    private List<ResponseDTO> ratings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<ResponseDTO> getRatings() {
        return ratings;
    }

    public void setRatings(List<ResponseDTO> ratings) {
        this.ratings = ratings;
    }
}
