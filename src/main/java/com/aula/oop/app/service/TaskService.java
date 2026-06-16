package com.aula.oop.app.service;

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

    public void deletar(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskResponseDTO alterar(Long id, TaskDTO tarefaNova) {
        Task tarefaParaAlterar = taskRepository.findById(id).orElseThrow();
        tarefaParaAlterar.setTitle(tarefaNova.getTitle());
        tarefaParaAlterar.setDescription(tarefaNova.getDescription());
        tarefaParaAlterar.setCompleted(tarefaNova.getCompleted());
        taskRepository.save(tarefaParaAlterar);
        return entityToResponseDTO(tarefaParaAlterar);
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    public Task dtoToEntity(TaskDTO dto) {
        Task entity = new Task();

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
    }