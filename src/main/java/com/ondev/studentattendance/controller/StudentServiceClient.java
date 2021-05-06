package com.ondev.studentattendance.controller;

import com.ondev.studentattendance.entities.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface StudentServiceClient {
    @PostMapping()
    Student saveStudent(@RequestBody Student student);

    @GetMapping("/{id}")
    Student getStudentById(@PathVariable Long id);

    @PutMapping("/{id}")
    Student updateStudent(@RequestBody Student student ,@PathVariable Long id);

    @DeleteMapping("/{id}")
    void deleteStudentById(@PathVariable Long id);

    @GetMapping()
    List<Student> getAllStudents();
}
