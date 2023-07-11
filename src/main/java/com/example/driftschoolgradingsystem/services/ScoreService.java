package com.example.driftschoolgradingsystem.services;

import com.example.driftschoolgradingsystem.dto.SubjectDTO;
import com.example.driftschoolgradingsystem.dto.SubjectMeanDTO;
import com.example.driftschoolgradingsystem.entities.Score;
import com.example.driftschoolgradingsystem.exceptions.StudentNotFoundException;
import com.example.driftschoolgradingsystem.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final SubjectService subjectService;
    @Autowired
    public ScoreService(ScoreRepository scoreRepository, SubjectService subjectService) {
        this.scoreRepository = scoreRepository;
        this.subjectService = subjectService;
    }

    //get all scores
    public List<Score> getScores(){
        return (List<Score>) scoreRepository.findAll();

    }

    public Score addScore(Score scores) {
        Optional<Score> scoreOptional = scoreRepository
                .findScoreByStudentAdmAndSubjectId(
                scores.getStudent().getAdm(),scores.getSubject().getId());
        if (scoreOptional.isPresent()) {
            throw new StudentNotFoundException("score already exists");
        }
        return scoreRepository.save(scores);
    }




    @Transactional
    public void updateScore(Long studentAdm, Long subjectId, int score) {

        Score studentScore = getScoreByStudent(studentAdm,subjectId);
        if (score == studentScore.getScore()) {
            throw new StudentNotFoundException("Score no update");
        }
        studentScore.setScore(score);

    }
    public void deleteScores(Long adm, Long subjectId) {
        Boolean exist = scoreRepository.existsById(subjectId) &&
                scoreRepository.findByAdmAndSubjectId(adm, subjectId) != null;

        if (!exist) {
            throw new IllegalStateException("Subject with id: "+subjectId +" does not exist for student adm:"+adm);
        }
        scoreRepository.deleteByAdmAndSubjectId(adm,subjectId);
    }

    public Score getScoreByStudent(Long adm, Long subjectId){
        return scoreRepository.findScoreByStudentAdmAndSubjectId(adm,subjectId)
                .orElseThrow(()->new StudentNotFoundException(adm));
    }


    public SubjectMeanDTO getMeanScoreBySubject(String subjectName) {
        List<Score> subjectScore = scoreRepository.findScoreBySubjectName(subjectName);
        if (subjectScore.size()<1){
            throw  new StudentNotFoundException("Subject Does not exist");
        }
        int total = 0;
        double mean = 0.0;

        for (Score singlescore: subjectScore){

            total+=singlescore.getScore();
            mean = total/subjectScore.size();
        }
        SubjectMeanDTO data = new SubjectMeanDTO();
        data.setName(subjectName);
        data.setMean(mean);

        return data;
    }

}
