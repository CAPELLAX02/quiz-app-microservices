package com.capellax.quiz_service.service;

import com.capellax.quiz_service.dao.QuizDAO;
import com.capellax.quiz_service.model.Question;
import com.capellax.quiz_service.model.QuestionWrapper;
import com.capellax.quiz_service.model.Quiz;
import com.capellax.quiz_service.model.Response;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizDAO quizDAO;
//    private final QuestionDAO questionDAO;

    @Transactional
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

//        List<Question> questions = questionDAO.findRandomQuestionsByCategory(category, numQ);
//        System.out.println("Retrieved Questions: " + questions);
//
//        Quiz quiz = new Quiz();
//        quiz.setTitle(title);
//        quiz.setQuestions(questions);
//        quizDAO.save(quiz);
//        quizDAO.flush();

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quizOptional = quizDAO.findById(id);
        if (quizOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Question> questionsFromDB = quizOptional.get().getQuestions();
        if (questionsFromDB.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<QuestionWrapper> questionForUser = questionsFromDB.stream()
                .map(question -> new QuestionWrapper(
                        question.getId(),
                        question.getQuestionTitle(),
                        question.getOption1(),
                        question.getOption2(),
                        question.getOption3(),
                        question.getOption4()
                ))
                .toList();

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quizOptional = quizDAO.findById(id);
        if (quizOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Quiz quiz = quizOptional.get();
        List<Question> questions = quiz.getQuestions();

        int rightAnswerCounter = 0;

        for (int i = 0; i < responses.size() && i < questions.size(); i++) {
            if (responses.get(i).getResponse().equals(questions.get(i).getRightAnswer())) {
                rightAnswerCounter++;
            }
        }

        return new ResponseEntity<>(rightAnswerCounter, HttpStatus.OK);
    }

}
