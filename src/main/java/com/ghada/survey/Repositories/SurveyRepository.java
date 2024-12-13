package com.ghada.survey.Repositories;

import com.ghada.survey.Entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}

