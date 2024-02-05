package com.yuco.todoapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController =  @Controller에 @ResponseBody가 추가된 것
@RestController
public class TodoController {
    @PostMapping("/v1.0/todo")
    public ResponseEntity postTodo(){
        //TODO 일정 작성 기능
        return ResponseEntity.ok().build();
    }
}
