package com.aula.oop.app.repository;

import com.aula.oop.app.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class TaskRepository {
    // banco de dados
    private List<Task> tasks = new ArrayList<>();


    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task save(Task entity) {
        if(tasks.isEmpty()) {
            entity.setId(Long.valueOf(1));
        } else {
            Task ultimaTarefa = tasks.getLast();
            entity.setId(ultimaTarefa.getId() + 1);
        }
        tasks.add(entity);
        return entity;
    }
}
