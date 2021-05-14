package com.ondev.studentattendance.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpCustomException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int errorCode;
    private String errorResponse;

    public HttpCustomException(int errorCode) {
        super();
        this.errorCode = errorCode;

    }

    public HttpCustomException(int errorCode, String errorResponse) {
        super();
        this.errorCode = errorCode;
        this.errorResponse = errorResponse;
    }
}
