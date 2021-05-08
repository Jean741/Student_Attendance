package com.ondev.studentattendance.entities;

import com.ondev.studentattendance.enumeration.Status;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecord implements Serializable {
     
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Student student;
    private LocalDate date;
    private String reason;
    @Enumerated(EnumType.STRING)
    @NonNull
    private Status status;
}
