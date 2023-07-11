package com.example.driftschoolgradingsystem.repositories;

import com.example.driftschoolgradingsystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.adm=?1")
    Optional<Student> findStudentByAdm(Long adm);

    boolean existsByAdm(Long admission);

    boolean deleteStudentsByAdm(Long adm);

}
