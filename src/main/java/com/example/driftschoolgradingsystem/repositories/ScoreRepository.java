package com.example.driftschoolgradingsystem.repositories;

import com.example.driftschoolgradingsystem.entities.Score;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.support.Repositories;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends CrudRepository<Score,Long> {

List<Score> findScoreByStudentAdm(Long adm);

Optional<Score> findScoreByStudentAdmAndSubjectId(Long adm, Long subjectId);

@Query("SELECT s FROM Score s WHERE s.student.adm = ?1 AND s.subject.subject_id = ?2")
List<Score> findByAdmAndSubjectId(Long adm, Long subjectId);

@Modifying
@Query("DELETE FROM Score s WHERE s.student.adm = ?1 AND s.subject.subject_id = ?2")
void deleteByAdmAndSubjectId(Long adm, Long subjectId);
@Query("SELECT s FROM Score s WHERE s.subject.name = ?1")
List<Score> findScoreBySubjectName(String subjectName);
}
