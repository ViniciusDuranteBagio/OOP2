package com.aula.oop.app.controller;

import com.aula.oop.app.dto.TaskDTO;
import com.aula.oop.app.model.Task;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
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
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(tasks.stream().map(this::taskToDTO).toList()); //automaticamente retorna 200

    }
    @PostMapping
    public ResponseEntity<TaskDTO> criarTarefa(@RequestBody @Valid TaskDTO taskRecebida){
        Task entity = dtoToEntity(taskRecebida);
        tasks.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskRecebida);
    }

    private Task dtoToEntity(TaskDTO dto) {
        Task entity = new Task();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCompleted(dto.getCompleted());
        return entity;
    }

    private TaskDTO taskToDTO (Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.getCompleted());

        return dto;
    }


}