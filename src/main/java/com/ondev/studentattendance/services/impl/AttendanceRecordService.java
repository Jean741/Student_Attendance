package com.ondev.studentattendance.services.impl;

import com.ondev.studentattendance.converter.AttendanceRecordConverter;
import com.ondev.studentattendance.dao.AttendanceRecordRepository;
import com.ondev.studentattendance.dao.StudentRepository;
import com.ondev.studentattendance.dto.AttendanceRecordDto;
import com.ondev.studentattendance.entities.AttendanceRecord;
import com.ondev.studentattendance.entities.Student;
import com.ondev.studentattendance.exception.ApiErrors;
import com.ondev.studentattendance.exception.HttpCustomException;
import com.ondev.studentattendance.services.IAttendanceRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class AttendanceRecordService implements IAttendanceRecordService {

    private AttendanceRecordRepository attendanceRecordRepository;
    private StudentRepository studentRepository;

    @Autowired
    public AttendanceRecordService(AttendanceRecordRepository attendanceRecordRepository,
                                   StudentRepository studentRepository) {
        this.attendanceRecordRepository = attendanceRecordRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public AttendanceRecord saveAttendanceRecord(AttendanceRecordDto attendanceRecordDto, HttpServletRequest request) {
        checkSimilarAttendanceHasAlreadyBeenRecordedToday(attendanceRecordDto, request);
        Student student = studentRepository.findById(attendanceRecordDto.getIdStudent())
                .orElseThrow(() -> new HttpCustomException(HttpStatus.NOT_FOUND.value(), String.format(ApiErrors.OBJECT_NOT_FOUND_MESSAGE, "Student", attendanceRecordDto.getIdStudent())));
        AttendanceRecord attendanceRecord = AttendanceRecordConverter.dtoToModel(attendanceRecordDto, student);
        return attendanceRecordRepository.saveAndFlush(attendanceRecord);
    }

    @Override
    public List<AttendanceRecord> saveAttendanceRecord(List<AttendanceRecord> attendanceRecords) {
        return attendanceRecordRepository.saveAll(attendanceRecords);
    }

    @Override
    public AttendanceRecord getAttendanceRecordById(Long id) {
        return attendanceRecordRepository.findById(id)
                .orElseThrow(() -> new HttpCustomException(HttpStatus.NOT_FOUND.value(), String.format(ApiErrors.OBJECT_NOT_FOUND_MESSAGE, "Attendance", id)));
    }

    @Override
    public List<AttendanceRecord> getAllAttendanceRecords() {
        return attendanceRecordRepository.findAll();
    }

    @Override
    public AttendanceRecord updateAttendanceRecord(AttendanceRecordDto attendanceRecordDto) {
        return attendanceRecordRepository.saveAndFlush(AttendanceRecord.builder()
                .id(attendanceRecordDto.getId())
                .date(attendanceRecordDto.getDate())
                .status(attendanceRecordDto.getStatus())
                .reason(attendanceRecordDto.getReason())
                .build());
    }

    @Override
    public void deleteAttendanceRecordById(Long id) {
        attendanceRecordRepository.deleteById(id);
    }

    private void checkSimilarAttendanceHasAlreadyBeenRecordedToday(AttendanceRecordDto attendanceRecordDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long studentId = attendanceRecordDto.getIdStudent();
        if (session.getAttribute(String.valueOf(studentId)) != null) {
            LocalDate attendanceRecordedDate = (LocalDate) session.getAttribute(String.valueOf(studentId));
            if (attendanceRecordedDate.equals(LocalDate.now())) {
                log.warn("Attendance has already been recorded for this student with id : {} today {}.", studentId, LocalDate.now());
                throw new HttpCustomException(HttpStatus.CONFLICT.value(), String.format(ApiErrors.ATTENDANCE_ALREADY_RECORDED, studentId, LocalDate.now()));
            } else {
                log.info("Attendance has already been recorded for this student with id : {} , but it was not recorded today : {}", studentId, LocalDate.now());
                session.removeAttribute(String.valueOf(studentId));
                session.setAttribute(String.valueOf(studentId), LocalDate.now());
            }
        } else {
            session.setAttribute(String.valueOf(studentId), LocalDate.now());
        }
    }
}
