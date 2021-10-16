package com.mprog.traindemo1.service.exception;

import lombok.Getter;

public class AppException extends RuntimeException {
    @Getter
    protected Object [] params;

    public AppException(String code, Object ... params) {
        super(code);
        this.params = params;
    }

    public AppException(String code, Throwable cause, Object ... params) {
        super(code, cause);
        this.params = params;
    }

}
