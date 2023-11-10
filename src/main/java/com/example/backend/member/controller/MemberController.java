package com.example.backend.member.controller;

import com.example.backend.member.Exception.MemberException;
import com.example.backend.member.dto.request.JoinRequest;
import com.example.backend.member.dto.request.LoginRequest;
import com.example.backend.member.dto.response.JoinResponse;
import com.example.backend.member.dto.response.LoginResponse;
import com.example.backend.member.service.MemberService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> checkIdDuplicate(@RequestParam String id){
        System.out.println("UserController checkIdDuplicate " + new Date());

        HttpStatus status = service.checkIdDuplicate(id);
        return new ResponseEntity<>(status);
    }

    @PostMapping("/join")
    public ResponseEntity<JoinResponse> join(@Valid @RequestBody JoinRequest req) {
        System.out.println("UserController join " + new Date());

        return ResponseEntity.ok(service.join(req));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req) {
        System.out.println("UserController login " + new Date());

        return ResponseEntity.ok(service.login(req));
    }


    /* 요청 DTO 검증 예외처리 핸들러 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println("UserController handleMethodArgumentNotValidException " + new Date());

        BindingResult bs = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        bs.getFieldErrors().forEach(err -> {
            sb.append(String.format("[%s]: %s.\n입력된 값: %s",
                    err.getField(), err.getDefaultMessage(), err.getRejectedValue()));
        });

        // Map 으로 보낸다면 프론트에서 사용하기 더 편리할 듯 !
        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }

    /* 사용자 관련 요청 예외처리 핸들러 */
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<?> handleUserException(MemberException e) {
        System.out.println("UserController handlerUserException " + new Date());

        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }

}
