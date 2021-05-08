package com.ondev.studentattendance.services;

import java.util.List;

import com.ondev.studentattendance.entities.Student;

public interface IStudentService {
    Student saveStudent(Student student);
    List<Student> saveStudent(List<Student> students);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    Student updateStudent(Student student , Long id);
    void deleteStudentById(Long id);
}
