package com.example.driftschoolgradingsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "subjects")

public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long subject_id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    public Subject() {
    }

    public Subject(Long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Subject( String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Long id) {
        this.subject_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Constructors, getters, and setters
}

