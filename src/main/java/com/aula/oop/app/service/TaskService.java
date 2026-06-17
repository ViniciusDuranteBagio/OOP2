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
        return taskRepository.findAll();
    }

    public Task create(Task entity) {
        taskRepository.save(entity);
        return entity;
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

    public void delete(Long id) {
        taskRepository.delete(id);
    }

    public Task put(Long id, TaskDTO task) {
        Task taskToPut = taskRepository.findById(id).orElseThrow();
        return taskRepository.put(id, task);
    }

}
