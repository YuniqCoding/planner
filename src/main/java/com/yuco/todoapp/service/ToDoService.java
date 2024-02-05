package com.yuco.todoapp.service;

import com.yuco.todoapp.controller.ToDoRequestDto;
import com.yuco.todoapp.repository.ToDo;
import com.yuco.todoapp.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;

    // 할일 생성
    public ToDo createToDo(ToDoRequestDto dto){
        var newToDo = dto.toEntity();
        return toDoRepository.save(newToDo);
    }
}
