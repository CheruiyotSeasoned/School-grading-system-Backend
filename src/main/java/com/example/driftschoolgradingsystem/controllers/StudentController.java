package com.example.driftschoolgradingsystem.controllers;

import com.example.driftschoolgradingsystem.dto.SubjectMeanDTO;
import com.example.driftschoolgradingsystem.entities.Student;
import com.example.driftschoolgradingsystem.entities.Subject;
import com.example.driftschoolgradingsystem.dto.StudentRequest;
import com.example.driftschoolgradingsystem.dto.SubjectRequest;
import com.example.driftschoolgradingsystem.exceptions.StudentNotFoundException;
import com.example.driftschoolgradingsystem.services.StudentService;
import com.example.driftschoolgradingsystem.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final SubjectService subjectService;

    @GetMapping("/ranked")
    public List<Student> getRankedStudents() {
        return studentService.getRankedStudents();
    }
//    @PostMapping
//    public void addNewStudent(@RequestBody Student student){
//       if(studentService.checkStudentExists(student.getAdm())){
//           throw  new StudentNotFoundException("Student with Admission "+student.getAdm()+" Already Exist!");
//       }
//        studentService.addNewStudent(student);
//    }
//    @PostMapping("/add")
//    public ResponseEntity<String> addStudent(@RequestBody StudentRequest request) {
//        Student studentEntity = new Student();
//        studentEntity.setAdm(request.getAdm());
//        studentEntity.setFirstName(request.getFirstName());
//        studentEntity.setLastName(request.getLastName());
//        List<Subject> subjects = new ArrayList<>();
//        List<SubjectRequest> dataSubjects = request.getSubjects();
//        for (SubjectRequest subjectRequest : dataSubjects) {
//            Long subjectId = subjectRequest.getSubjectId();
//            Subject subject = subjectService.getSubjectById(subjectId);
//            subjects.add(subject);
//        }
//        studentEntity.setSubjects(subjects);
//        studentService.addNewStudent(studentEntity); // or studentService.saveStudent(studentEntity);
//        return ResponseEntity.ok("Student added successfully");
//    }
@PostMapping("/add")
public ResponseEntity<String> addStudent(@RequestBody StudentRequest request) {
    int compulsoryCount = 0;
    int scienceCount = 0;
    int otherCount = 0;


    List<Subject> subjects = new ArrayList<>();


    for (SubjectRequest subjectRequest : request.getSubjects()) {
        Long subjectId = subjectRequest.getSubjectId();
        Subject subject = subjectService.getSubjectById(subjectId);

        // compulsory
        if (subject.getCategory().equalsIgnoreCase("Compulsory")) {
            compulsoryCount++;
        }

       //sciemce
        if (subject.getCategory().equalsIgnoreCase("Sciences")) {
            scienceCount++;
        }

        // subjects
        subjects.add(subject);
    }


    if (compulsoryCount != 3) {
        throw new StudentNotFoundException("Compulsory subjects are missing.");
    }

    if (scienceCount < 2) {
        throw new StudentNotFoundException("Minimum of two science subjects is required.");
    }

    otherCount = 2;
    if (request.getSubjects().size() != 8) {
        throw new StudentNotFoundException("Invalid number of subjects. Must have exactly 8 subjects.");
    }

    int totalOtherCount = request.getSubjects().size() - compulsoryCount - scienceCount;
    if (totalOtherCount != otherCount) {
        throw new StudentNotFoundException("Incorrect number of other subjects.");
    }

    Student studentEntity = new Student();
    studentEntity.setAdm(request.getAdm());
    studentEntity.setFirstName(request.getFirstName());
    studentEntity.setLastName(request.getLastName());
    studentEntity.setSubjects(subjects);

    studentService.addNewStudent(studentEntity);

    return ResponseEntity.ok("Student added successfully");
}




    @DeleteMapping(path = "{studentAdm}")
    public void deleteStudent(@PathVariable("studentAdm") Long studentAdm){
        studentService.deleteStudent(studentAdm);
    }
    @PutMapping(path = "{studentAdm}")
    public void updateStudent(
            @PathVariable("studentAdm") Long studentAdm,
            @RequestParam(required = false) String fname,
            @RequestParam(required = false) String lname
    ){
        studentService.updateStudent(studentAdm, fname, lname);

    }



}
