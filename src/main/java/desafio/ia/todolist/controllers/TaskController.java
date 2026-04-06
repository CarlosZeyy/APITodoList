package desafio.ia.todolist.controllers;

import desafio.ia.todolist.DTO.TaskDto;
import desafio.ia.todolist.entity.Task;
import desafio.ia.todolist.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createTask(@RequestBody TaskDto taskDto) {
        var taskId = taskService.createTask(taskDto);

        var response = new ResponseMessage("Tarefa criada com sucesso!");

        return ResponseEntity.created(URI.create("/tasks/" + taskId)).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        var tasks = taskService.getTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable("taskId") Long taskId) {
        var task = taskService.getTaskById(taskId);

        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            throw new EntityNotFoundException("Task ID not found");
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<ResponseMessage> updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskDto taskDto) {
        taskService.updateTaskById(taskId, taskDto);

        var response = new ResponseMessage("Tarefa atualizada com sucesso");

        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTaskById(taskId);

        return ResponseEntity.noContent().build();
    }
}
