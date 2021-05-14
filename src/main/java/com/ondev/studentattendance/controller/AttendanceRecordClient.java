package com.ondev.studentattendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ondev.studentattendance.dto.AttendanceRecordDto;
import com.ondev.studentattendance.entities.AttendanceRecord;

public interface AttendanceRecordClient {
    @PostMapping()
    public AttendanceRecord saveAttendanceRecord(@RequestBody AttendanceRecordDto attendanceRecord, HttpServletRequest request);

    @GetMapping("/{id}")
    public AttendanceRecord getAttendanceRecordById(@PathVariable Long id);

    @PutMapping()
    public AttendanceRecord updateAttendanceRecord(@RequestBody AttendanceRecordDto attendanceRecordDto);

    @DeleteMapping("/{id}")
    public void deleteAttendanceRecordById(@PathVariable Long id);

    @GetMapping()
    public List<AttendanceRecord> getAllAttendanceRecords();
}
