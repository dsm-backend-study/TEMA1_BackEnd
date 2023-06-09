package com.example.todo.domain.todo.service;

import com.example.todo.domain.todo.Entity.Todo;
import com.example.todo.domain.todo.controller.dto.request.TodoCreateRequest;
import com.example.todo.domain.todo.controller.dto.request.TodoUpdateRequest;
import com.example.todo.domain.todo.controller.dto.response.TodoListResponse;
import com.example.todo.domain.todo.controller.dto.response.TodoResponse;
import com.example.todo.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public void addTodo(
            TodoCreateRequest request
    ) {
        todoRepository.save(
                Todo.builder()
                        .content(request.getContent())
                        .build());
    }

    public void modifyTodo(
            Long todoId,
            TodoUpdateRequest request
    ) {
        todoRepository.findById(todoId)
                .orElseThrow()
                .updateTodo(request.getContent());
    }

    @Transactional(readOnly = true)
    public TodoListResponse listTodo() {
        return TodoListResponse.builder()
                .todoResponseList(todoRepository.findAll()
                        .stream()
                        .map(TodoResponse::of)
                        .toList())
                .build();
    }

    public void removeTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow();
        todoRepository.deleteById(todoId);
    }

    public void clickCheckBox(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow();
        todo.clickCheckBox();
    }


}
