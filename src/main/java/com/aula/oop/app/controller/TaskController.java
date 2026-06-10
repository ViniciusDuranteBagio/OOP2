package com.aula.oop.app.controller;

import com.aula.oop.app.dto.TaskDTO;
import com.aula.oop.app.dto.TaskResponseDTO;
import com.aula.oop.app.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefa")
public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponseDTO> listAll() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public TaskResponseDTO createTask(@RequestBody @Valid TaskDTO tarefa) {
        return taskService.createTask(tarefa);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable Long id, @RequestBody @Valid TaskDTO tarefa) {
        return taskService.updateTask(id, tarefa);
    }

    @DeleteMapping("/deletar/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);

    }
}
