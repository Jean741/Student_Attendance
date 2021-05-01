package com.ondev.studentattendance.controller;

import com.ondev.studentattendance.entities.Student;
import com.ondev.studentattendance.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {
    private IStudentService studentService;

    @Autowired
    public StudentRestController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public Student saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping()
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody Student student ,@PathVariable Long id){
        return studentService.updateStudent(student,id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
    }
}
