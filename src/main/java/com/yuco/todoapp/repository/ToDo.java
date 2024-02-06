package com.yuco.todoapp.repository;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="todo_id",nullable = false)
    private Long todoId;

    private String title;
    private String content;
    private String userName;
    private String password;
    private LocalDateTime createdAt;

    @Builder
    public ToDo(String title, String content, String userName, String password){
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
