package com.ondev.studentattendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ondev.studentattendance.dto.AttendanceRecordDto;
import com.ondev.studentattendance.entities.AttendanceRecord;
import com.ondev.studentattendance.services.IAttendanceRecordService;

@RestController
@RequestMapping("/api/attendance-record")
public class AttendanceRecordRestController implements AttendanceRecordClient {
    private IAttendanceRecordService attendanceRecordService;

    @Autowired
    public AttendanceRecordRestController(IAttendanceRecordService attendanceRecordService) {
        this.attendanceRecordService = attendanceRecordService;
    }

    public AttendanceRecord saveAttendanceRecord(@RequestBody AttendanceRecord attendanceRecord){
        return attendanceRecordService.saveAttendanceRecord(attendanceRecord);
    }

    public AttendanceRecord getAttendanceRecordById(@PathVariable Long id){
        return attendanceRecordService.getAttendanceRecordById(id);
    }


    public List<AttendanceRecord> getAllAttendanceRecords(){
        return attendanceRecordService.getAllAttendanceRecords();
    }

    public AttendanceRecord updateAttendanceRecord(@RequestBody AttendanceRecordDto attendanceRecordDto){
        return attendanceRecordService.updateAttendanceRecord(attendanceRecordDto);
    }

    public void deleteAttendanceRecordById(@PathVariable Long id){
    	attendanceRecordService.deleteAttendanceRecordById(id);
    }
}
