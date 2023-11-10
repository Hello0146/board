package com.example.backend.member.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberException extends RuntimeException{

    private HttpStatus status;

    public MemberException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }



}
