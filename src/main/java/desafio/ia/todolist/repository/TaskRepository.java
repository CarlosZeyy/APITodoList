package desafio.ia.todolist.repository;
import desafio.ia.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {

}
