package com.ondev.studentattendance.services;

import com.ondev.studentattendance.dto.AttendanceRecordDto;
import com.ondev.studentattendance.entities.AttendanceRecord;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAttendanceRecordService {
    AttendanceRecord saveAttendanceRecord(AttendanceRecordDto attendanceRecordDto, HttpServletRequest httpServletRequest);

    List<AttendanceRecord> saveAttendanceRecord(List<AttendanceRecord> attendanceRecords);

    AttendanceRecord getAttendanceRecordById(Long id);

    List<AttendanceRecord> getAllAttendanceRecords();

    AttendanceRecord updateAttendanceRecord(AttendanceRecordDto attendanceRecordDto);

    void deleteAttendanceRecordById(Long id);
}
