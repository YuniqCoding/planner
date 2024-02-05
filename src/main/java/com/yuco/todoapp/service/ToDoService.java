package com.yuco.todoapp.service;

import com.yuco.todoapp.controller.ToDoRequestDto;
import com.yuco.todoapp.repository.ToDo;
import com.yuco.todoapp.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
