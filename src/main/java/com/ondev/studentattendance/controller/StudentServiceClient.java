package com.ondev.studentattendance.controller;

import com.ondev.studentattendance.entities.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface StudentServiceClient {
    @PostMapping()
    public Student saveStudent(@RequestBody Student student);

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id);

    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody Student student ,@PathVariable Long id);

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id);

    @GetMapping()
    public List<Student> getAllStudents();
}
