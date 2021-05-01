package com.ondev.studentattendance.dto;

import com.ondev.studentattendance.enumeration.Status;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttendanceRecordDto {
    private Long id;
    private String studentName;
    private LocalDate date;
    private String reason;
    private Status status;
}
