package com.example.driftschoolgradingsystem.dto;

public class SubjectDTO {
    private String name;
    private String category;

    // Constructors, getters, and setters

    // Constructor
    public SubjectDTO(String name, String category) {
        this.name = name;
        this.category = category;
    }

    // Getters and Setters
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
}
