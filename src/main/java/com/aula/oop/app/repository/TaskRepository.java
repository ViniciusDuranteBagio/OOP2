package com.aula.oop.app.repository;

import com.aula.oop.app.dto.TaskDTO;
import com.aula.oop.app.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {




//    // banco de dados
//    private List<Task> tasks = new ArrayList<>();
//
//
//    public List<Task> getAllTasks() {
//        return tasks;
//    }
//
//    public Task save(Task entity) {
//        if(tasks.isEmpty()) {
//            entity.setId(Long.valueOf(1));
//        } else {
//            Task ultimaTarefa = tasks.getLast();
//            entity.setId(ultimaTarefa.getId() + 1);
//        }
//        tasks.add(entity);
//        return entity;
//    }
//
//    public void delete(Long id) {
//        Task taskForDelete = null;
//        for(Task entity : tasks) {
//            if (entity.getId() == id) {
//                taskForDelete = entity;
//            }
//        }
//        tasks.remove(taskForDelete);
//    }
//
//    public Task put(Long id, TaskDTO task) {
//        for(Task entity : tasks) {
//            if (entity.getId() == id) {
//                entity.setTitle(entity.getTitle());
//                entity.setDescription(entity.getDescription());
//                entity.setCompleted(entity.getCompleted());
//                return entity;
//            }
//        }
//        return null;
//    }

}
