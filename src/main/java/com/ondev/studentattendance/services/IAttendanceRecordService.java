package com.ondev.studentattendance.services;

import java.util.List;

import com.ondev.studentattendance.dto.AttendanceRecordDto;
import com.ondev.studentattendance.entities.AttendanceRecord;

public interface IAttendanceRecordService {
    AttendanceRecord saveAttendanceRecord(AttendanceRecord attendanceRecord);
    List<AttendanceRecord> saveAttendanceRecord(List<AttendanceRecord> attendanceRecords);
    AttendanceRecord getAttendanceRecordById(Long id);
    List<AttendanceRecord> getAllAttendanceRecords();
    AttendanceRecord updateAttendanceRecord(AttendanceRecordDto attendanceRecordDto);
    void deleteAttendanceRecordById(Long id);
}