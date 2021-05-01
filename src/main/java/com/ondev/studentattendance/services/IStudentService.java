package com.ondev.studentattendance.services;

import com.ondev.studentattendance.entities.Student;

import java.util.List;

public interface IStudentService {
    Student saveStudent(Student student);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    Student updateStudent(Student student , Long id);
    void deleteStudentById(Long id);
}
