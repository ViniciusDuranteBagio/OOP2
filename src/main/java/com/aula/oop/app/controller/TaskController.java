package com.aula.oop.app.controller;

import com.aula.oop.app.dto.TaskDTO;
import com.aula.oop.app.model.Task;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    // é o nosso banco de dados
    private List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> Listall(){
        return tasks;
    }

    @PostMapping
    public Task createTask(@RequestBody @Valid TaskDTO tarefa){
        Task entity = convertDTOToEnity(tarefa);
        tasks.add(entity);
        return entity;

    }

    public Task convertDTOToEnity(TaskDTO dto){
        Task entity = new Task();
      //  entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescreption(dto.getDescreption());
        entity.setCompleted(dto.getCompleted());
        return entity;

    }

}
