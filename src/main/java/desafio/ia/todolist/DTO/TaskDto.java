package desafio.ia.todolist.DTO;

import desafio.ia.todolist.entity.TaskStatus;

import java.time.Instant;

public record TaskDto(String title, String description, Instant finalDate, TaskStatus status) {
}
