package com.aula.oop.app.controller;

import com.aula.oop.app.dto.TaskDTO;
import com.aula.oop.app.dto.TaskResponseDTO;
import com.aula.oop.app.model.Task;
import com.aula.oop.app.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    public TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> listAll() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public TaskResponseDTO createTask(@RequestBody @Valid TaskDTO tarefa) {
        Task entity = taskService.convertDTOToEntity(tarefa);
        taskService.create(entity);
        TaskResponseDTO responseDTO = taskService.convertEntityToResponseDTO(entity);
        return responseDTO;
    }

    @DeleteMapping("/{id}")
    public deletebyId (@PathVariable Long id)




}
