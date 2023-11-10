package com.example.backend.member.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;




@Entity
@Getter
@Setter
@ToString
public class Member {

    @Id
    private String id;
    private String pwd;
    private String name;
    private String email;

    public Member(){
    }

    public Member(String id, String pwd, String name, String email){
        super();
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
    }


}

