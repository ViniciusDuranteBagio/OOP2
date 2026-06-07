package com.aula.oop.app.repository;

import com.aula.oop.app.model.Task;
import org.hibernate.query.sqm.mutation.internal.TableKeyExpressionCollector;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    // é nosso banco de dados
    private List<Task> tasks = new ArrayList<>();
    private Long id;

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task save(Task entity) {
        tasks.add(entity);
        id = entity.getId();
        if  (id == null || id == 0) {
            entity.setId((tasks.size() - 1 )+ 1L);
            return entity;
        } else if (id == id) {
            id++;
            return entity;
        } else {
            return entity;
        }
    }

    public Task getLastTask(Task entity) {
        Task lastTask = tasks.get(0);
        for (int i = 0; i < tasks.size(); i++) {
            lastTask = tasks.get(i);
        }
        return lastTask;
    }
}
