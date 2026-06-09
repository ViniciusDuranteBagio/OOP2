package com.aula.oop.app.service;

import com.aula.oop.app.dto.TaskRequestDTO;
import com.aula.oop.app.dto.TaskResponseDTO;
import com.aula.oop.app.model.Task;
import com.aula.oop.app.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        Task task = new Task();
        task.setId(taskRepository.getLastId());
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setCompleted(false);

        return entityToResponseDTO(taskRepository.save(task));
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.getAllTaks()
                .stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    public Optional<TaskResponseDTO> updateTask(Long id, TaskRequestDTO taskRequestDTO) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(taskRequestDTO.getTitle());
                    task.setDescription(taskRequestDTO.getDescription());

                    return entityToResponseDTO(task);
                });
    }

    public boolean deleteTask(Long id) {
        return taskRepository.deleteById(id);
    }

    public TaskResponseDTO entityToResponseDTO(Task task) {
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setCompleted(task.getCompleted());

        return taskResponseDTO;
    }
}