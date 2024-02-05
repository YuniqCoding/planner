package com.yuco.todoapp.controller;

import com.yuco.todoapp.repository.ToDo;
import com.yuco.todoapp.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @RestController =  @Controller에 @ResponseBody가 추가된 것
@RestController
@AllArgsConstructor
public class TodoController {

    public final ToDoService toDoService;
    @PostMapping("/v1.0/todo")
    public ResponseEntity<ToDoResponseDto> postTodo(@RequestBody ToDoRequestDto dto){
        //TODO 일정 작성 기능
        ToDo todo = toDoService.createToDo(dto);
        ToDoResponseDto response = new ToDoResponseDto(todo);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/v1.0/todo/{todoId}")
    public ResponseEntity<ToDoResponseDto> getTodo(@PathVariable Long todoId){
        ToDo todo = toDoService.getToDo(todoId);
        ToDoResponseDto response = new ToDoResponseDto(todo);
        return ResponseEntity.ok().body(response);
    }
}
