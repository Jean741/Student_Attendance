 package com.ondev.studentattendance.converter;

import com.ondev.studentattendance.dto.AttendanceRecordDto;
import com.ondev.studentattendance.entities.AttendanceRecord;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class AttendanceRecordConverter {
    private AttendanceRecordConverter(){
        super();
    }

    public static AttendanceRecordDto modelToDto(AttendanceRecord attendanceRecord){
        return  AttendanceRecordDto.builder()
                .id(attendanceRecord.getId())
                .studentName(attendanceRecord.getStudent().getName())
  //              .date(attendanceRecord.getDate())
                .reason(attendanceRecord.getReason())
                .status(attendanceRecord.getStatus())
                .build();
    }

    public static List<AttendanceRecordDto> modelsToDtos(Collection<AttendanceRecord> attendanceRecords){
        return attendanceRecords.stream().filter(Objects::nonNull).map(AttendanceRecordConverter::modelToDto).collect(Collectors.toList());
    }
}
