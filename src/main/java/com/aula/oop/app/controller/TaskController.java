import com.aula.oop.app.dto.TaskResponseDTO;
import com.aula.oop.app.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponseDTO> listAll() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public TaskResponseDTO createTask(@RequestBody @Valid TaskDTO tarefa) {
        return taskService.createTask(tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        taskService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public void alterar(@PathVariable Long id, @RequestBody @Valid TaskDTO tarefa) {
        taskService.alterar(id, tarefa);
    }

}