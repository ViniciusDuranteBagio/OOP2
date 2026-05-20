package com.aula.oop.app.controller;


import com.aula.oop.app.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/api/task")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(tasks);
    };

    @PostMapping
    public ResponseEntity<Task> criarTarefa(@RequestBody Task taskNew) {
        tasks.add(taskNew);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskNew);
    };
};

