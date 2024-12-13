package com.ghada.survey.Repositories;

import com.ghada.survey.Entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {

    List<Response> findByQuestionId(Long questionId);
}
