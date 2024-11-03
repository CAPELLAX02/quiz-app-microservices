package com.capellax.question_service.dao;

import com.capellax.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {

    List<Question> findByCategoryIgnoreCase(String category);

    @Query(
            value = "SELECT * FROM question_service_db.public.question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ",
            nativeQuery = true
    )
    List<Question> findRandomQuestionsByCategory(
            String category,
            int numQ
    );

}
