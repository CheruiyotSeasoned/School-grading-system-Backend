package com.example.driftschoolgradingsystem.entities;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity

@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name="admission")
    private Long adm;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;

    @Column(name = "total_points")
    private int totalPoints;
    @Column(name = "total_marks")
    private int totalMarks;
    @Column(name = "rank")
    private int rank;

    @Column(name = "grade")
    private String grade;
    public Student() {
    }

    public Student(Long id, String firstName, String lastName, Long adm, List<Subject> subjects, int totalPoints, int totalMarks, int rank, String grade) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adm = adm;
        this.subjects = subjects;
        this.totalPoints = totalPoints;
        this.totalMarks = totalMarks;
        this.rank = rank;
        this.grade = grade;
    }

    public Student(String firstName, String lastName, Long adm, List<Subject> subjects, int totalPoints, int totalMarks, int rank, String grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adm = adm;
        this.subjects = subjects;
        this.totalPoints = totalPoints;
        this.totalMarks = totalMarks;
        this.rank = rank;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAdm() {
        return adm;
    }

    public void setAdm(Long adm) {
        this.adm = adm;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", adm=" + adm +
                '}';
    }


    // Other student attributes, getters, and setters
}
