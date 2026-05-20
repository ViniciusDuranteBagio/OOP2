package com.aula.oop.app.controller;

import com.aula.oop.app.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    @GetMapping
    public List<Task> listAll() {
        return tasks;
    }

    @PostMapping
    public Task createTask(@RequestBody Task tarefa) {
        tasks.add(tarefa);
        return tarefa;
    }
}
