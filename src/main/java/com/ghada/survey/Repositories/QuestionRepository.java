package com.ghada.survey.Repositories;

import com.ghada.survey.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
