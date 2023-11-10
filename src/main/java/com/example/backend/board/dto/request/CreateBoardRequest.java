package com.example.backend.board.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateBoardRequest {

    private int id;
    private String title;
    private String content;
    private LocalDateTime createDate;


}
