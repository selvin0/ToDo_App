package ToDoApp.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import ToDoApp.Model.ToDo;
public interface ToDoRepository extends JpaRepository<ToDo,Long>
{

}
