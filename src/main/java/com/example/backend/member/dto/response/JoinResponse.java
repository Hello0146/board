package com.example.backend.member.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinResponse {

    private String id;

    public JoinResponse(String id){
        this.id = id;
    }
}
