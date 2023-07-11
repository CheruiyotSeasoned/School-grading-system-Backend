package com.example.driftschoolgradingsystem.controllers;

import com.example.driftschoolgradingsystem.dto.SubjectMeanDTO;
import com.example.driftschoolgradingsystem.entities.Score;
import com.example.driftschoolgradingsystem.entities.Subject;
import com.example.driftschoolgradingsystem.exceptions.StudentNotFoundException;
import com.example.driftschoolgradingsystem.services.ScoreService;
import com.example.driftschoolgradingsystem.services.StudentService;
import com.example.driftschoolgradingsystem.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/scores")
public class ScoreController {
    private final ScoreService scoreService;
    private final StudentService studentService;
    private final SubjectService subjectService;

//list all scores
    @GetMapping("/list")
    public List<Score> getScores(){

        return scoreService.getScores();
    }
    //AddScores

    @PostMapping("/add")
    public ResponseEntity<String> addScore(@RequestBody ScoreRequest request) {
        Score scoreEntity = new Score();
        scoreEntity.setStudent(studentService.getStudentByAdm(request.getAdm())
                .orElseThrow(() -> new StudentNotFoundException(request.getAdm())));
        scoreEntity.setSubject(subjectService.getSubjectById(request.getSubjectId()));
        scoreEntity.setScore(request.getScore());
        scoreService.addScore(scoreEntity); // or scoreService.saveScore(scoreEntity);
        return ResponseEntity.ok("Score added successfully");
    }


    @DeleteMapping(path = "{studentAdm}/{subjectId}")
    public void deleteSubject(@PathVariable("studentAdm") Long studentAdm,
                              @PathVariable("subjectId") Long subjectId) {
        scoreService.deleteScores(studentAdm,subjectId);
    }

    @PutMapping(path = "{studentAdm}/{subjectId}")
    public void updateSubject(
            @PathVariable("studentAdm") Long studentAdm,
            @PathVariable("subjectId") Long subjectId,
            @RequestBody UpdateScoreRequest request
    ) {
        scoreService.updateScore(studentAdm, subjectId, request.getScore());
    }
    public static class UpdateScoreRequest {
        private Integer score;

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }
    }
    public static class ScoreRequest {
        private Long adm;
        private Long subjectId;
        private int score;

        public Long getAdm() {
            return adm;
        }

        public Long getSubjectId() {
            return subjectId;
        }

        public int getScore() {
            return score;
        }
        // Add getters and setters
    }

    @GetMapping("/subject-mean/{suject_name}")
    public SubjectMeanDTO getMeanBySubject(
            @PathVariable("suject_name") String subject_name){
        return scoreService.getMeanScoreBySubject(subject_name);
    }



}
