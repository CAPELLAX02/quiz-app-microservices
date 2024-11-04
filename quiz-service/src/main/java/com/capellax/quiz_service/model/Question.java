package com.capellax.quiz_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String category;

    private String difficultyLevel;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String questionTitle;

    private String rightAnswer;

}
