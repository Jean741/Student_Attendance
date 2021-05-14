package com.ondev.studentattendance.exception;

public final class ApiErrors {

    public static final String OBJECT_NOT_FOUND_MESSAGE = "%s with id : %d not found";
    public static final String ATTENDANCE_ALREADY_RECORDED = "Attendance has already been recorded for this student with id : %d today %s.";

    private ApiErrors() {
    }
}
