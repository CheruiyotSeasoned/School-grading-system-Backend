package com.example.driftschoolgradingsystem.services;

import com.example.driftschoolgradingsystem.entities.Subject;
import com.example.driftschoolgradingsystem.exceptions.StudentNotFoundException;
import com.example.driftschoolgradingsystem.utils.ScoreCalculator;
import com.example.driftschoolgradingsystem.entities.Score;
import com.example.driftschoolgradingsystem.entities.Student;
import com.example.driftschoolgradingsystem.repositories.ScoreRepository;
import com.example.driftschoolgradingsystem.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository, ScoreRepository scoreRepository) {
        this.studentRepository = studentRepository;
        this.scoreRepository = scoreRepository;
    }

//    public List<Student> getRankedStudents() {
//        List<Student> students = (List<Student>) studentRepository.findAll();
//        // Calculate total marks and points for each student
//        for (Student student : students) {
//            List<Score> subjectScores = scoreRepository.findScoreByStudentAdm(student.getAdm());
//            int totalMarks = 0;
//            int totalPoints = 0;
//            for (Score score : subjectScores) {
//                totalMarks += score.getScore();
//                totalPoints += ScoreCalculator.calculatePoints(score.getScore());
//            }
//
//            // Set the calculated total marks and points for the student
//            student.setTotalMarks(totalMarks);
//            student.setTotalPoints(totalPoints);
//
//            // Calculate final grade based on total points
//            double meanPoints = (double) totalPoints / subjectScores.size();
//
//            // Calculate the final grade based on the mean
//            String finalGrade = ScoreCalculator.calculateFinalGrade(meanPoints);
//
//            // Set the final grade for the student
//            student.setGrade(finalGrade);
//
//            // Update the student in the database
//            studentRepository.save(student);
//        }
//        // Sort the students based on total points
//        students.sort(Comparator.comparingInt(Student::getTotalPoints).reversed());
//        // Assign ranks to students
//        int rank = 1;
//        for (int i = 0; i < students.size(); i++) {
//            Student student = students.get(i);
//            if (i > 0 && student.getTotalPoints() != students.get(i - 1).getTotalPoints()) {
//                rank = i + 1;
//            }
//            student.setRank(rank);
//        }
//        return students;
//    }
    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByAdm(student.getAdm());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Student Already saved");
        }
        System.out.println(student);
        studentRepository.save(student);
    }

    public boolean checkStudentExists(Long adm) {
        return studentRepository.existsByAdm(adm);
    }
    public Optional<Student> getStudentByAdm(Long adm){
        return studentRepository.findStudentByAdm(adm);

    }


    public void deleteStudent(Long adm) {
        Boolean exist = studentRepository.existsByAdm(adm);
        if (!exist) {
            throw new StudentNotFoundException(adm);
//            throw new IllegalStateException("Student with id: "+adm +" does not exist");
        }
        studentRepository.deleteStudentsByAdm(adm);
    }
    @Transactional
    public void updateStudent(Long adm, String fname,String lname) {
        Student student = studentRepository.findStudentByAdm(adm)
                .orElseThrow(()-> new StudentNotFoundException(adm));
        if (fname !=null &&
                fname.length()>0 &&
                !Objects.equals(student.getFirstName(),fname)){
            student.setFirstName(fname);
        }
        if (lname !=null &&
                lname.length()>0 &&
                !Objects.equals(student.getLastName(),fname)){
            student.setLastName(lname);
        }

    }

    public List<Student> getRankedStudents() {
        List<Student> students = (List<Student>) studentRepository.findAll();

        for (Student student : students) {
            List<Score> subjectScores = scoreRepository.findScoreByStudentAdm(student.getAdm());

            int totalMarks = 0;
            int totalPoints = 0;
            List<Score> compulsoryScores = new ArrayList<>();
            List<Score> scienceScores = new ArrayList<>();
            List<Score> otherScores = new ArrayList<>();

            // Categorize subject scores
            for (Score score : subjectScores) {
                Subject subject = score.getSubject();

                if (subject.getCategory().equalsIgnoreCase("Compulsory")) {
                    compulsoryScores.add(score);
                } else if (subject.getCategory().equalsIgnoreCase("Sciences")) {
                    scienceScores.add(score);
                } else {
                    otherScores.add(score);
                }
            }

            // Calculate total marks and points for compulsory subjects
            for (Score score : compulsoryScores) {
                totalMarks += score.getScore();
                totalPoints += ScoreCalculator.calculatePoints(score.getScore());
            }

            // Sort scienceScores in descending order based on score
            scienceScores.sort(Comparator.comparingInt(Score::getScore).reversed());

            // Include the best two science scores
            for (int i = 0; i < 2 && i < scienceScores.size(); i++) {
                Score score = scienceScores.get(i);
                totalMarks += score.getScore();
                totalPoints += ScoreCalculator.calculatePoints(score.getScore());
            }

            // Include the remaining two scores from other subjects
            for (int i = 0; i < 2 && i < otherScores.size(); i++) {
                Score score = otherScores.get(i);
                totalMarks += score.getScore();
                totalPoints += ScoreCalculator.calculatePoints(score.getScore());
            }

            // Set the calculated total marks and points for the student
            student.setTotalMarks(totalMarks);
            student.setTotalPoints(totalPoints);

            // Calculate final grade based on total points
            double meanPoints = (double) totalPoints / subjectScores.size();

            // Calculate the final grade based on the mean
            String finalGrade = ScoreCalculator.calculateFinalGrade(meanPoints);

            // Set the final grade for the student
            student.setGrade(finalGrade);

            // Update the student in the database
            studentRepository.save(student);
        }

        // Sort the students based on total points
        students.sort(Comparator.comparingInt(Student::getTotalPoints).reversed());

        // Assign ranks to students
        int rank = 1;
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (i > 0 && student.getTotalPoints() != students.get(i - 1).getTotalPoints()) {
                rank = i + 1;
            }
            student.setRank(rank);
        }

        return students;
    }

}
