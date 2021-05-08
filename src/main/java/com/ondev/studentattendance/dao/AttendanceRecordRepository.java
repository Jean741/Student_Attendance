package com.ondev.studentattendance.dao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ondev.studentattendance.entities.AttendanceRecord;
import com.ondev.studentattendance.entities.Student;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord,Long> {
	AttendanceRecord findByStudentAndDate(Student student,LocalDate date);
	
}
