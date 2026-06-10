package com.aula.oop.app.repository;

import com.aula.oop.app.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class TaskRepository {
    long id;
    long lastId;
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task save(Task entity) {
        tasks.add(entity);
        entity.setId(lastId);
        return entity;

    }

    public long getId() {
        return lastId;
    }
}
