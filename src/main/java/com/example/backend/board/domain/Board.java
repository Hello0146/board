package com.example.backend.board.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 게시물 고유 번호

    private String uid; // 사용자 아이디

    private String title; // 게시물 제목

    private String content; // 게시물 내용

    private LocalDateTime createDate; // 작성일

    private int del; //게시물 삭제 상태 0,1

    private int readCount; // 조회수

    public Board(int id, String uid, String title,String content ,LocalDateTime createDate, int del, int readCount){

        this.id = id;
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.del = del;
        this.readCount = readCount;
    }

    public Board(int id, String title, String content){

        this.id = id;
        this.title = title;
        this.content = content;
    } //  이 생성자는 새로운 게시글을 작성할 때 사용됩니다.


}
