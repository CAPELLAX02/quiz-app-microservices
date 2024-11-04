package com.capellax.quiz_service.dao;

import com.capellax.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDAO extends JpaRepository<Quiz, Integer> {



}
