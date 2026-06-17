package com.aula.oop.app.service;

import com.aula.oop.app.dto.TaskDTO;
import com.aula.oop.app.dto.TaskResponseDTO;
import com.aula.oop.app.model.Task;
import com.aula.oop.app.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public Task create(Task entity) {
        taskRepository.save(entity);
        return entity;
    }

    public void deletar(Long id){
        taskRepository.deletar(id);
    }
    public Task alterar(Long id, TaskDTO tarefa){
        return taskRepository.alterar(id, tarefa);
    }

    public Task convertDTOToEntity(TaskDTO dto) {
        Task entity = new Task();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCompleted(dto.getCompleted());

        return entity;
    }

    public TaskResponseDTO convertEntityToResponseDTO(Task entity) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(entity.getId());
        dto.setCompleted(entity.getCompleted());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
