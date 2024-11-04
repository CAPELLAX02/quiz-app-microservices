package com.capellax.quiz_service.service;

import com.capellax.quiz_service.dao.QuizDAO;
import com.capellax.quiz_service.feign.QuizInterface;
import com.capellax.quiz_service.model.QuestionWrapper;
import com.capellax.quiz_service.model.Quiz;
import com.capellax.quiz_service.model.Response;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizDAO quizDAO;
    private final QuizInterface quizInterface;

    @Transactional
    public ResponseEntity<String> createQuiz(
            String category,
            int numQ,
            String title
    ) {
        List<Integer> questionIds = quizInterface
                .getQuestionsForQuiz(category, numQ)
                .getBody();

        Quiz quiz = new Quiz();

        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);

        quizDAO.save(quiz);

        return new ResponseEntity<>("Quiz created successfully.", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(
            Integer id
    ) {
        Quiz quiz = quizDAO.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(
            Integer id,
            List<Response> responses
    ) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }

}
