package com.yuco.todoapp.controller;

import com.yuco.todoapp.CommonResponse;
import com.yuco.todoapp.repository.ToDo;
import com.yuco.todoapp.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CommonResponse<ToDoResponseDto>> postTodo(@RequestBody ToDoRequestDto dto){
        //TODO 일정 작성 기능
        ToDo todo = toDoService.createToDo(dto);
        ToDoResponseDto response = new ToDoResponseDto(todo);
        return ResponseEntity.ok()
                .body(CommonResponse.<ToDoResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("생성이 완료되었습니다.")
                        .data(response)
                        .build());
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<CommonResponse<ToDoResponseDto>> getTodo(@PathVariable Long todoId) {
        ToDo todo = toDoService.getToDo(todoId);
        ToDoResponseDto response = new ToDoResponseDto(todo);
        return ResponseEntity.ok()
                .body(CommonResponse.<ToDoResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("단건 조회가 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<ToDoResponseDto>>> getTodos() {
        List<ToDo> todos = toDoService.getToDos();
        List<ToDoResponseDto> response = todos.stream()
                .map(ToDoResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(CommonResponse.<List<ToDoResponseDto>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("목록 조회이 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<CommonResponse<ToDoResponseDto>> putTodo(@PathVariable Long todoId, @RequestBody ToDoRequestDto dto) {
        ToDo todo = toDoService.updateToDo(todoId, dto);
        ToDoResponseDto response = new ToDoResponseDto(todo);
        return ResponseEntity.ok()
                .body(CommonResponse.<ToDoResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("수정이 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<CommonResponse> deleteTodo(@PathVariable Long todoId, @RequestBody ToDoRequestDto dto) {
        toDoService.deleteToDo(todoId, dto.getPassword());
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("삭제가 완료 되었습니다.")
                .build());
    }

}
