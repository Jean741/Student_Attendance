package com.ondev.studentattendance.dao;

import com.ondev.studentattendance.entities.AttendanceRecord;
import com.ondev.studentattendance.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    AttendanceRecord findByStudentAndDate(Student student, LocalDate date);
}
