package com.project.conquer.school.teacher.exception;

public class CustomException extends RuntimeException{
    public CustomException(Enum value) {
        super(value.toString());
    }

    public CustomException(Exception e) {
        super(e);
    }

    public CustomException(String s) {
        super(s);
    }
}
