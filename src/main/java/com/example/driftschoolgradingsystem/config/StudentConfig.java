package com.example.driftschoolgradingsystem.config;
import com.example.driftschoolgradingsystem.entities.Student;
import com.example.driftschoolgradingsystem.entities.Subject;
import com.example.driftschoolgradingsystem.repositories.StudentRepository;
import com.example.driftschoolgradingsystem.repositories.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, SubjectRepository subjectRepository){
        return args -> {


//            Student student = new Student();
//            student.setFirstName("John");
//            student.setLastName("brian");
//            student.setAdm(12345L);
//
//// Create the subject list for the student
//            List<Subject> subjects = new ArrayList<>();
//            subjects.add(subjectRepository.findById(1L).orElseThrow()); // Assuming subject ID 1 exists
//            subjects.add(subjectRepository.findById(2L).orElseThrow()); // Assuming subject ID 2 exists
//            student.setSubjects(subjects);
//            studentRepository.save(student);

        };

    }
}
