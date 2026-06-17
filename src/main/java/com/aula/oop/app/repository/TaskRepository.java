package com.aula.oop.app.repository;

import com.aula.oop.app.dto.TaskDTO;
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
    public void deletar(Long id){
        Task tarefaParaDeletar = null;
        for(Task entity : tasks){
            if(entity.getId() == id){
                tarefaParaDeletar = entity;
            }
        }
        tasks.remove(tarefaParaDeletar);
    }
    public Task alterar(Long id, TaskDTO tarefa){
        for(Task entity : tasks){
            if(entity.getId() == id){
                entity.setTitle(tarefa.getTitle());
                entity.setDescription(tarefa.getDescription());
                entity.setCompleted(tarefa.getCompleted());
                return entity;
            }
        }
    }
}
