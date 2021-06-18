package com.example.todoserver.controller;

import com.example.todoserver.model.TodoEntity;
import com.example.todoserver.model.TodoReq;
import com.example.todoserver.model.TodoRes;
import com.example.todoserver.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoRes> create(@RequestBody TodoReq request){
        log.info("Create");

        if(ObjectUtils.isEmpty(request.getTitle()))
            return ResponseEntity.badRequest().build();

        if(ObjectUtils.isEmpty(request.getOrder()))
            request.setOrder(0L);
        if(ObjectUtils.isEmpty(request.getCompleted()))
            request.setCompleted(false);

        TodoEntity result = this.todoService.add(request);
        return ResponseEntity.ok(new TodoRes(result));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoRes> readOne(@PathVariable Long id){
        log.info("READ One");
        TodoEntity result = this.todoService.searchById(id);
        return ResponseEntity.ok(new TodoRes(result));
    }

    @GetMapping
    public ResponseEntity<List<TodoRes>> readAll(){
        log.info("READ ALL");
        List<TodoEntity> list = this.todoService.sarchAll();
        List<TodoRes> response = list.stream().map(TodoRes::new)
                                                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    @PatchMapping("{id}")
    public ResponseEntity<TodoRes> update(@PathVariable Long id, @RequestBody TodoReq request){
        log.info("UPDATE");

        TodoEntity result = this.todoService.updateById(id, request);
        return ResponseEntity.ok(new TodoRes(result));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id){
        log.info("DELETE ONE");
        this.todoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        log.info("DELETE All");
        this.todoService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
