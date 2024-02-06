package com.yuco.todoapp.service;

import com.yuco.todoapp.controller.ToDoRequestDto;
import com.yuco.todoapp.repository.ToDo;
import com.yuco.todoapp.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;

    // 할일 생성
    public ToDo createToDo(ToDoRequestDto dto){
        var newToDo = dto.toEntity();
        return toDoRepository.save(newToDo);
    }

    // 할일 단건 조회
    public ToDo getToDo(Long todoId){
        return toDoRepository.findById(todoId)
                .orElseThrow(IllegalArgumentException::new);
    }

    // 할일 전체 조회
    public List<ToDo> getToDos(){
        return toDoRepository.findAll(Sort.by("createdAt").descending());
    }

    // 할일 수정
    public ToDo updateToDo(Long todoId, ToDoRequestDto dto) {
        ToDo todo = checkPWAndGetToDo(todoId, dto.getPassword());

        todo.setTitle(dto.getTitle());
        todo.setContent(dto.getContent());
        todo.setUserName(dto.getUserName());
        return toDoRepository.save(todo);
    }

    private ToDo checkPWAndGetToDo(Long todoId, String password) {
        ToDo todo = getToDo(todoId);

        // 비밀번호 체크
        if(todo.getPassword() != null && !Objects.equals(todo.getPassword(), password))
            throw new IllegalArgumentException();
        return todo;
    }

    public void deleteToDo(Long todoId, String password){
        ToDo todo = checkPWAndGetToDo(todoId,password);
        toDoRepository.delete(todo);
    }
}
