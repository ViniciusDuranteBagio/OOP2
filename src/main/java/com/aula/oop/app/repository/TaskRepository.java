package com.aula.oop.app.repository;

import com.aula.oop.app.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getAllTaks() {
        return tasks;
    }

    public Task save(Task task) {
        tasks.add(task);
        return task;
    }

    public Long getLastId() {
        if (tasks.isEmpty()) {
            return Long.valueOf(1);
        }
        return tasks.getLast().getId() + 1;
    }
}