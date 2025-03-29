package com.example.todo.controller;

import com.example.todo.config.BaseException;
import com.example.todo.dto.TodoItemDto;
import com.example.todo.entity.TodoItem;
import com.example.todo.entity.TodoStatus;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
//@CrossOrigin(origins ="http://localhost:3000")
public class TodoController {


    @Autowired
    private TodoService todoService;

    @PostMapping
    public TodoItem createTodoItem(@RequestBody TodoItemDto todoItemDto) throws BaseException {
        return todoService.createTodoItem(todoItemDto);
    }

    @GetMapping
    public List<TodoItem> getAllTodoItems(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TodoItem> pageResult = todoService.getAllTodoItems(pageable);
        return pageResult.getContent();
    }


    @GetMapping("/{id}")
    public TodoItem getTodoItemById(@PathVariable(value = "id") Long id) throws BaseException {
        return todoService.getTodoItemById(id);
    }

    @PutMapping("/{id}")
    public TodoItem updateTodoItem(@PathVariable Long id, @RequestBody TodoItemDto todoItemDto) throws BaseException {
        return todoService.updateTodoItem(id, todoItemDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoItem(@PathVariable(value = "id") Long id) throws BaseException{
        todoService.deleteTodoItem(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchByName")
    public List<TodoItem> getTodoItemsByName(@RequestParam String name, @RequestParam int page, @RequestParam int size) throws BaseException {
        return todoService.getTodoItemsByName(name, page, size);
    }

    @GetMapping("/searchByStatus")
    public List<TodoItem> getTodoItemsByStatus(@RequestParam TodoStatus status, @RequestParam int page, @RequestParam int size) throws BaseException {
        return todoService.getTodoItemsByStatus(status, page, size);
    }

    @GetMapping("/searchByMonth")
    public List<TodoItem> getTodoItemsByMonth(@RequestParam int month, @RequestParam int page, @RequestParam int size) throws BaseException {
        return todoService.getTodoItemsByMonth(month, page, size);
    }

    @GetMapping("/searchByOldest")
    public List<TodoItem> getTodoItemsByOldest(@RequestParam int page, @RequestParam int size) throws BaseException {
        return todoService.getTodoItemsByOldest(page, size);
    }

    @GetMapping("/searchByLatest")
    public List<TodoItem> getTodoItemsByLatest(@RequestParam int page, @RequestParam int size) throws BaseException {
        return todoService.getTodoItemsByLatest(page, size);
    }

    @GetMapping("/completionRatio")
    public ResponseEntity<String> getCompletionRatio() {
        String ratio = todoService.getCompletionRatio();
        return ResponseEntity.ok(ratio);
    }


}
