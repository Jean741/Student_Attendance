package com.ondev.studentattendance.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ondev.studentattendance.controller.StudentRestController;
import com.ondev.studentattendance.entities.Student;
import com.ondev.studentattendance.services.impl.StudentService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(value = StudentRestController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentRestControllerTest {

    private static Student student1;
    private static Student student2;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;
    private String URI = "/api/students";

    static void loadData() {
        student1 = new Student(1L, "Jean", "jean@gmail.com");
        student2 = new Student(2L, "Mohamed", "mohamed@gmail.com");
    }

    @BeforeAll
    static void setUp() {
        loadData();
    }

    @Order(1)
    @Test
    @DisplayName("Save new user")
    void testSaveNewStudent() throws Exception {
        String jsonInput = this.convertToJsonString(student1);
        Mockito.when(studentService.saveStudent(any(Student.class))).thenReturn(student1);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        Assertions.assertEquals(jsonInput, jsonOutput);
        Mockito.verify(studentService, Mockito.times(1)).saveStudent(student1);

    }

    @Order(2)
    @Test
    @DisplayName("Get student by id")
    void testGetStudentById() throws Exception {

        String jsonInput = this.convertToJsonString(student1);
        Mockito.when(studentService.getStudentById(1L)).thenReturn(student1);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI + "/1").accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        Assertions.assertEquals(jsonInput, jsonOutput);
        Assertions.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

    }

    @Order(3)
    @Test
    @DisplayName("Get All students")
    void testGetAllStudents() throws Exception {
        List<Student> students = Arrays.asList(student1, student2);
        String jsonInput = this.convertToJsonString(students);
        Mockito.when(studentService.getAllStudents()).thenReturn(students);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        Assertions.assertEquals(jsonInput, jsonOutput);
    }


    @Order(4)
    @Test
    @DisplayName("Update student")
    void testUpdateStudent() throws Exception {
        Mockito.when(studentService.updateStudent(student1, 1L)).thenReturn(student1);
        student1.setName("Claude");
        student1.setEmail("claude@gmail.com");
        String jsonInput = this.convertToJsonString(student1);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI + "/1").accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        Assertions.assertEquals(jsonInput, jsonOutput);
    }


    @Order(5)
    @Test
    @DisplayName("Delete student")
    void testDeleteStudentById() throws Exception {
        Mockito.doNothing().when(studentService).deleteStudentById(1L);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI + "/1").accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
    }

    /**
     * Convert Object into Json String by using Jackson ObjectMapper
     *
     * @param student
     * @return
     * @throws JsonProcessingException
     */
    private String convertToJsonString(Object student) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(student);

    }
}
