package com.ondev.studentattendance.entities;

import com.ondev.studentattendance.enumeration.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Student student;
    private LocalDate date;
    private String reason;
    @Enumerated(EnumType.STRING)
    private Status status;
}
