package com.capellax.question_service.controller;

import com.capellax.question_service.model.Question;
import com.capellax.question_service.model.QuestionWrapper;
import com.capellax.question_service.model.Response;
import com.capellax.question_service.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    private final Environment environment;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(
            @PathVariable String category
    ) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(
            @RequestBody Question question
    ) {
        return questionService.addQuestion(question);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(
            @RequestParam String categoryName,
            @RequestParam Integer numQuestions
    ) {
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(
            @RequestBody List<Integer> questionIds
    ) {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionFromId(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(
            @RequestBody List<Response> responses
    ) {
        return questionService.getScore(responses);
    }

}
