package com.aula.oop.app.service;

import com.aula.oop.app.dto.TaskDTO;
import com.aula.oop.app.dto.TaskResponseDTO;
import com.aula.oop.app.model.Task;
import com.aula.oop.app.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponseDTO createTask(TaskDTO tarefa) {
        Task entity = dtoToEntity(tarefa);
        return entityToResponseDTO(taskRepository.save(entity));
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    public TaskResponseDTO updateTask(Long id, TaskDTO tarefa) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        task.setTitle(tarefa.getTitle());
        task.setDescription(tarefa.getDescription());
        task.setCompleted(tarefa.getCompleted());

        return entityToResponseDTO(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private Task dtoToEntity(TaskDTO dto) {
        Task entity = new Task();

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCompleted(dto.getCompleted());

        return entity;
    }

    private TaskResponseDTO entityToResponseDTO(Task entity) {
        TaskResponseDTO dto = new TaskResponseDTO();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCompleted(entity.getCompleted());

        return dto;
    }
}