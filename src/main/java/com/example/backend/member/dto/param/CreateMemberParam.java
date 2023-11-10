package com.example.backend.member.dto.param;

import com.example.backend.member.domain.Member;
import com.example.backend.member.dto.request.JoinRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateMemberParam {

    private String id;
    private String pwd;
    private String name;
    private String email;

    public CreateMemberParam(JoinRequest req, String encodedPwd){
        this.id = req.getId();
        this.pwd = encodedPwd;
        this.name = req.getName();
        this.email = req.getEmail();
    }

    public Member toEntity() {
        return new Member(id, pwd, name, email);
    }
}
