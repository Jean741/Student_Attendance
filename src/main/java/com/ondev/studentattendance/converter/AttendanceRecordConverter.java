 package com.ondev.studentattendance.converter;

import com.ondev.studentattendance.dto.AttendanceRecordDto;
import com.ondev.studentattendance.entities.AttendanceRecord;
import com.ondev.studentattendance.entities.Student;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class AttendanceRecordConverter {
    private AttendanceRecordConverter() {
        super();
    }

    public static AttendanceRecordDto modelToDto(AttendanceRecord attendanceRecord) {
        return AttendanceRecordDto.builder()
                .id(attendanceRecord.getId())
                .idStudent(attendanceRecord.getStudent().getId())
                .studentName(attendanceRecord.getStudent().getName())
  //              .date(attendanceRecord.getDate())
                .reason(attendanceRecord.getReason())
                .status(attendanceRecord.getStatus())
                .build();
    }

    public static AttendanceRecord dtoToModel(AttendanceRecordDto attendanceRecordDto, Student student) {
        return AttendanceRecord.builder()
                .id(attendanceRecordDto.getId())
                .student(student)
                .date(attendanceRecordDto.getDate())
                .reason(attendanceRecordDto.getReason())
                .status(attendanceRecordDto.getStatus())
                .build();
    }

    public static List<AttendanceRecordDto> modelsToDtos(Collection<AttendanceRecord> attendanceRecords) {
        return attendanceRecords.stream().filter(Objects::nonNull).map(AttendanceRecordConverter::modelToDto).collect(Collectors.toList());
    }
}
