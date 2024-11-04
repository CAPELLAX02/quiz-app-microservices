package com.capellax.quiz_service.controller;

import com.capellax.quiz_service.model.QuestionWrapper;
import com.capellax.quiz_service.model.Response;
import com.capellax.quiz_service.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(
            @RequestParam String category,
            @RequestParam int numQ,
            @RequestParam String title
    ) {
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(
            @PathVariable Integer id
    ) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(
        @PathVariable Integer id,
        @RequestBody List<Response> responses
    ) {
        return quizService.calculateResult(id, responses);
    }

}
