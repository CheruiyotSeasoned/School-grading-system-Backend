package com.example.driftschoolgradingsystem.services;

import com.example.driftschoolgradingsystem.dto.SubjectDTO;
import com.example.driftschoolgradingsystem.entities.Score;
import com.example.driftschoolgradingsystem.entities.Student;
import com.example.driftschoolgradingsystem.entities.Subject;
import com.example.driftschoolgradingsystem.exceptions.StudentNotFoundException;
import com.example.driftschoolgradingsystem.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getsubjects(){
        return subjectRepository.findAll();

    }
    public boolean checkSubjectExists(String name) {
        return subjectRepository.existsByName(name);
    }

    public Subject addNewSubject(Subject subject) {
        Optional<Subject> subjectOptional = subjectRepository.findSubjectByName(subject.getName());
        if (subjectOptional.isPresent()) {
            throw new IllegalStateException("Subject already exists");
        }

        return subjectRepository.save(subject);
    }

    public Subject getSubjectById(Long id){
        return subjectRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException(id));
    }

    public void deleteSubject(Long subjectId) {
        Boolean exist = subjectRepository.existsById(subjectId);
        if (!exist) {
            throw new IllegalStateException("Subject with id: "+subjectId +" does not exist");
        }
        subjectRepository.deleteById(subjectId);
    }
    @Transactional
    public void updateSubject(Long subjectId, String name, String category) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(()-> new IllegalStateException("Subject with id: "+subjectId +" does not exist"));
        if (name !=null &&
                name.length()>0 &&
                !Objects.equals(subject.getName(),name)){
            subject.setName(name);
        }
        if (category !=null &&
                category.length()>0 &&
                !Objects.equals(subject.getCategory(),category)){
            subject.setCategory(category);
        }

    }

    public List<Subject> getCompulsorySubjects() {
        return subjectRepository.findSubjectByCategory("Compulsory");
    }

    public Subject getSubjectByName(String name) {
        return subjectRepository.findbyName(name);
    }

}

