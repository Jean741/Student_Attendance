package com.ondev.studentattendance.services;


import com.ondev.studentattendance.dao.StudentRepository;
import com.ondev.studentattendance.entities.Student;
import com.ondev.studentattendance.services.impl.StudentService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Order;
import static org.mockito.ArgumentMatchers.any;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private static Student student;

    static void loadData(){
        student = new Student(1L,"Mohamed","mdiaby@gmail.com");
    }

    @BeforeAll
    void setUp(){
        MockitoAnnotations.initMocks(this);
        loadData();
    }

    @Order(1)
    @Test
    @DisplayName("Save a new student")
    void testSaveNewStudent(){
        Mockito.when(studentRepository.saveAndFlush(any(Student.class))).thenReturn(student);
         Student s = studentService.saveStudent(new Student());
        Assertions.assertEquals(s.getName(),student.getName());
    }

    @Order(2)
    @Test
    @DisplayName("Get student by id")
    void testGetStudentById(){
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student s = studentService.getStudentById(1L);
        Assertions.assertEquals(1L,s.getId());
    }

    @Order(3)
    @Test
    void testGetStudentWithWrongId(){
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->studentService.getStudentById(2L));
        Assertions.assertEquals("Student with id : "+2L+" do not exist.",exception.getMessage());
    }

    @Order(4)
    @Test
    @DisplayName("Get All students")
    void testGetAllStudents(){
        Mockito.when(studentRepository.findAll()).thenReturn(Collections.singletonList(student));
        List<Student> students = studentService.getAllStudents();
         Assertions.assertEquals(1,students.size());
         Mockito.verify(studentRepository,Mockito.times(1)).findAll();
    }

    @Order(5)
    @Test
    @DisplayName("Update student")
    void testUpdateStudent(){
        Mockito.when(studentRepository.saveAndFlush(student)).thenReturn(student);
        student.setName("Jean");
        Student s = studentService.updateStudent(student,1L);
        Assertions.assertEquals("Jean",s.getName());
    }

    @Order(6)
    @Test
    @DisplayName("Delete student by id")
    void testDeleteStudent(){
       Mockito.doNothing().when(studentRepository).deleteById(1L);
       studentService.deleteStudentById(1L);
       Mockito.verify(studentRepository,Mockito.times(1)).deleteById(1L);
    }
}
