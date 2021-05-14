package com.ondev.studentattendance;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.ondev.studentattendance.dao.AttendanceRecordRepository;
import com.ondev.studentattendance.entities.AttendanceRecord;
import com.ondev.studentattendance.entities.Student;
import com.ondev.studentattendance.enumeration.Status;
import com.ondev.studentattendance.services.IStudentService;

@SpringBootApplication
public class StudentAttendanceApplication extends SpringBootServletInitializer implements CommandLineRunner {
	@Autowired
	IStudentService studentService;
	@Autowired
	AttendanceRecordRepository attendanceRecordRepository;

	public static void main(String[] args) {
		SpringApplication.run(StudentAttendanceApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StudentAttendanceApplication.class);
	}
	@Override
	public void run(String... args) throws Exception {
		
		Student student = Student.builder()
				.name("Jean Claude")
				.email("jean@gmail.com")
				.build();
		Student student1 = Student.builder()
				.name("Mohamed Diaby")
				.email("mdiaby@gmail.com")
				.build();

		studentService.saveStudent(student);
		studentService.saveStudent(student1);
		
		AttendanceRecord attendanceRecord = AttendanceRecord.builder()
				.date(LocalDate.parse("2021-05-02"))
				.reason("Maladie")
				.student(student)
				.status(Status.ABSENT)
				.build();
		AttendanceRecord attendanceRecord1 = AttendanceRecord.builder()
				.date(LocalDate.parse("2021-05-02"))
				.reason("nor maladie")
				.student(student1)
				.status(Status.PRESENT)
				.build();
		
		attendanceRecordRepository.save(attendanceRecord);
		attendanceRecordRepository.save(attendanceRecord1);
		
	}
}
