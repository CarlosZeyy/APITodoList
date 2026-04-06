package desafio.ia.todolist.service;

import desafio.ia.todolist.DTO.TaskDto;
import desafio.ia.todolist.entity.Task;
import desafio.ia.todolist.entity.TaskStatus;
import desafio.ia.todolist.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.awt.*;
import java.time.Instant;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Long createTask(TaskDto taskDto) {
        var task = new Task(null, taskDto.title(), taskDto.description(), taskDto.finalDate(), TaskStatus.PENDENTE, Instant.now(), null);

        var taskSaved = taskRepository.save(task);

        return taskSaved.getTaskId();
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void updateTaskById(Long id, TaskDto taskDto) {
        var taskExists = taskRepository.findById(id);

        if (taskExists.isPresent()) {
            var currentTask = taskExists.get();

            if (taskDto.title() != null) {
                currentTask.setTitle(taskDto.title());
            }

            if (taskDto.description() != null) {
                currentTask.setDescription(taskDto.description());
            }

            if (taskDto.finalDate() != null) {
                currentTask.setFinalDate(taskDto.finalDate());
            }

            if (taskDto.status() != null) {
                currentTask.setStatus(taskDto.status());
            }

            taskRepository.save(currentTask);
        } else {
            throw new EntityNotFoundException("Task ID not found");
        }
    }

    public void deleteTaskById(Long id) {
        var taskExists = taskRepository.existsById(id);

        if (taskExists) {
            taskRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Task ID not found");
        }
    }
}
