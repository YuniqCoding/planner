package com.yuco.todoapp.controller;

import com.yuco.todoapp.repository.ToDo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDoRequestDto {
    private String title;
    private String content;
    private String userName;
    private String password;

    public ToDo toEntity(){
        return ToDo.builder()
                .title(title)
                .content(content)
                .userName(userName)
                .password(password)
                .build();
    }
}
