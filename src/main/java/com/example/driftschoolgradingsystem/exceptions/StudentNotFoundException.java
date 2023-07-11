package com.example.driftschoolgradingsystem.exceptions;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Long adm){
        super("Student with Admission "+adm +" Not Found");
    }
    public StudentNotFoundException(String message){
        super(message);
    }
}

