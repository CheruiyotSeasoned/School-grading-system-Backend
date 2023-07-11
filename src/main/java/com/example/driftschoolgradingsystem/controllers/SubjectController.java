package com.example.driftschoolgradingsystem.controllers;

import com.example.driftschoolgradingsystem.entities.Subject;
import com.example.driftschoolgradingsystem.exceptions.StudentNotFoundException;
import com.example.driftschoolgradingsystem.repositories.SubjectRepository;
import com.example.driftschoolgradingsystem.services.SubjectService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subject")
public class SubjectController {

    private final SubjectService subjectService;


    @GetMapping("/list")
    public List<Subject> getSubjects(){
        return subjectService.getsubjects();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSubjects(@RequestBody List<Subject> subjects) {
        for (Subject subject : subjects) {
            if (subjectService.checkSubjectExists(subject.getName())) {
                subjectService.updateSubject(subject.getId(),subject.getName(),subject.getCategory());
            }else{
                subjectService.addNewSubject(subject);
            }
        }
        return ResponseEntity.ok("Subjects added successfully");
    }


    @DeleteMapping(path = "{subjectId}")
    public void deleteSubject(@PathVariable("subjectId") Long subjectId) {
        subjectService.deleteSubject(subjectId);
    }

    @PutMapping(path = "{subjectId}")
    public void updateSubject(
            @PathVariable("subjectId") Long subjectId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category
    ) {
        subjectService.updateSubject(subjectId, name, category);
    }


}
