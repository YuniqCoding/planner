package com.yuco.todoapp.controller;

import com.yuco.todoapp.repository.ToDo;
import com.yuco.todoapp.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// @RestController =  @Controller에 @ResponseBody가 추가된 것
@RequestMapping("/v1.0/todo")
@RestController
@AllArgsConstructor
public class TodoController {

    public final ToDoService toDoService;
    @PostMapping
    public ResponseEntity<ToDoResponseDto> postTodo(@RequestBody ToDoRequestDto dto){
        //TODO 일정 작성 기능
        ToDo todo = toDoService.createToDo(dto);
        ToDoResponseDto response = new ToDoResponseDto(todo);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<ToDoResponseDto> getTodo(@PathVariable Long todoId){
        ToDo todo = toDoService.getToDo(todoId);
        ToDoResponseDto response = new ToDoResponseDto(todo);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping
    public ResponseEntity<List<ToDoResponseDto>> getTodos(){
        List<ToDo> todo = toDoService.getToDos();
        List<ToDoResponseDto> response = todo.stream()
                .map(ToDoResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }
}
