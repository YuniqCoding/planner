package com.yuco.todoapp.controller;

import com.yuco.todoapp.repository.ToDo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ToDoResponseDto {
    private Long todoId;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime createdAt;

    public ToDoResponseDto(ToDo todo){
        this.todoId = todo.getTodoId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.userName = todo.getUserName();
        this.createdAt = todo.getCreatedAt();
    }
}
