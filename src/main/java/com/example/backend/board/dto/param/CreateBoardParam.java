package com.example.backend.board.dto.param;

import com.example.backend.board.dto.request.CreateBoardRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBoardParam {
    private int id;
    private String uid;
    private String title;
    private String content;

    public CreateBoardParam(CreateBoardRequest req){
        this.id = req.getId();
        this.title = req.getTitle();
        this.content = req.getContent();
    }





}
