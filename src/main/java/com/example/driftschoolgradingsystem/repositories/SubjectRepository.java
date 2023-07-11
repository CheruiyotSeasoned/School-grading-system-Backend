package com.example.driftschoolgradingsystem.repositories;

import com.example.driftschoolgradingsystem.entities.Student;
import com.example.driftschoolgradingsystem.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("SELECT s FROM Subject s WHERE s.name=?1")
    Optional<Subject> findSubjectByName(String name);
    boolean existsByName(String name);
    @Query("SELECT s FROM Subject s WHERE s.name=?1")
    Subject findbyName(String name);


    List<Subject> findSubjectByCategory(String category);
}
