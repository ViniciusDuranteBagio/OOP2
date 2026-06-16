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

    public TaskResponseDTO createTask(TaskDTO tarefa) {
        Task entity = dtoToEntity(tarefa);
        return entityToResponseDTO(taskRepository.save(entity));
    }

    public TaskResponseDTO updateTask(Long id, TaskDTO tarefa) {
        Task entity = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("tarefa não encontrada"));
        entity.setTitle(tarefa.getTitle());
        entity.setDescription(tarefa.getDescription());
        entity.setCompleted(tarefa.getCompleted());
        return entityToResponseDTO(taskRepository.save(entity));

    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.getAllTaks()
                .stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    public void deleteTask(Long id) {
       // Task tarefaParaDeletar = taskRepository.findById(id).orElseThrow()
        taskRepository.deleteById(id);
    }

    public Task dtoToEntity(TaskDTO dto) {
        Task entity = new Task();
        entity.setId(taskRepository.getLastId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCompleted(dto.getCompleted());
        return entity;
    }

    public TaskResponseDTO entityToResponseDTO(Task entity) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCompleted(entity.getCompleted());
        return dto;
    }
}
