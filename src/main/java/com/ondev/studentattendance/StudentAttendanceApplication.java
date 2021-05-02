package com.ondev.studentattendance;

import com.ondev.studentattendance.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class StudentAttendanceApplication extends SpringBootServletInitializer implements CommandLineRunner {
	@Autowired
	IStudentService studentService;

	public static void main(String[] args) {
		SpringApplication.run(StudentAttendanceApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StudentAttendanceApplication.class);
	}
	@Override
	public void run(String... args) throws Exception {
	}
}
