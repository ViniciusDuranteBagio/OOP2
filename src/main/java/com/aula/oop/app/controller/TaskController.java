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
@RequestMapping("/api/task")
public class TaskController {

    public TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    public List<Task> ListAll() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public TaskResponseDTO createTask(@RequestBody @Valid TaskDTO tarefa) {
        Task entity = taskService.convertDTOToEntitiy(tarefa);
        taskService.create(entity);
        TaskResponseDTO responseDTO = taskService.convertEntityToResponseDTO(entity);
        return responseDTO;
    }

    @GetMapping("/last")
    public  TaskResponseDTO getLastTask(@RequestBody @Valid TaskDTO tarefa) {
        Task entity = taskService.convertDTOToEntitiy(tarefa);
        taskService.lastTask(entity);
        TaskResponseDTO responseDTO = taskService.convertEntityToResponseDTO(entity);
        return responseDTO;
    }
}
