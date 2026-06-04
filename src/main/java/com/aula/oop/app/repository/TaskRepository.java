package com.aula.oop.app.repository;

import com.aula.oop.app.model.Task;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    // é nosso banco de dados
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task save(Task entity) {
        tasks.add(entity);
        return entity;
    }
}
