package com.aula.oop.app.repository;

import com.aula.oop.app.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    public List<Task> getAllTaks() {
        return tasks;
    }

    public Task save(Task task) {
        tasks.add(task);
        return task;
    }

    public Optional<Task> findById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    public boolean deleteById(Long id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }

    public Long getLastId() {
        if (tasks.isEmpty()) {
            return 1L;
        }

        return tasks.stream()
                .mapToLong(Task::getId)
                .max()
                .orElse(0L) + 1L;
    }
}