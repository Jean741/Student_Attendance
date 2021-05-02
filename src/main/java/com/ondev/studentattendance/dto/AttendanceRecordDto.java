package com.ondev.studentattendance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ondev.studentattendance.enumeration.Status;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "studentName",
        "date",
        "reason",
        "status"
})
public class AttendanceRecordDto {
    private Long id;
    private String studentName;
    private LocalDate date;
    private String reason;
    private Status status;
}
