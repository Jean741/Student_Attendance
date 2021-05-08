package com.ondev.studentattendance.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ondev.studentattendance.dao.AttendanceRecordRepository;
import com.ondev.studentattendance.dao.StudentRepository;
import com.ondev.studentattendance.dto.AttendanceRecordDto;
import com.ondev.studentattendance.entities.AttendanceRecord;
import com.ondev.studentattendance.services.IAttendanceRecordService;

@Service
public class AttendanceRecordServiceImpl implements IAttendanceRecordService {

	private AttendanceRecordRepository attendanceRecordRepository;
	private StudentRepository studentRepository;

	@Autowired
	public AttendanceRecordServiceImpl(AttendanceRecordRepository attendanceRecordRepository,
			StudentRepository studentRepository) {
		this.attendanceRecordRepository = attendanceRecordRepository;
		this.studentRepository = studentRepository;
	}

	@Override
	public AttendanceRecord saveAttendanceRecord(AttendanceRecord attendanceRecord) {
		return attendanceRecordRepository.saveAndFlush(attendanceRecord);
	}

	@Override
	public List<AttendanceRecord> saveAttendanceRecord(List<AttendanceRecord> attendanceRecords) {
		return attendanceRecordRepository.saveAll(attendanceRecords);
	}

	@Override
	public AttendanceRecord getAttendanceRecordById(Long id) {
		return attendanceRecordRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("attendanceRecord with id : " + id + " do not exist."));
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
}
