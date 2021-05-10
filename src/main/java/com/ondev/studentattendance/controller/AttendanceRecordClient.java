package com.ondev.studentattendance.controller;

import com.ondev.studentattendance.dto.AttendanceRecordDto;
import com.ondev.studentattendance.entities.AttendanceRecord;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
