package com.ondev.studentattendance.services.impl;

import com.ondev.studentattendance.dao.StudentRepository;
import com.ondev.studentattendance.entities.Student;
import com.ondev.studentattendance.exception.ApiErrors;
import com.ondev.studentattendance.exception.HttpCustomException;
import com.ondev.studentattendance.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> saveStudent(List<Student> students) {
        return null;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new HttpCustomException(HttpStatus.NOT_FOUND.value(), String.format(ApiErrors.OBJECT_NOT_FOUND_MESSAGE, "Student", id)));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        student.setId(id);
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}
