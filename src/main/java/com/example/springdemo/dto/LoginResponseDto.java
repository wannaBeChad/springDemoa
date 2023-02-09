package com.example.springdemo.dto;

public class LoginResponseDto {
    String msg;
    String error;

    public LoginResponseDto(String msg, String error) {
        this.msg = msg;
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
