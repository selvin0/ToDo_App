package ToDoApp.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ToDoApp.Model.ToDo;
import ToDoApp.Repo.ToDoRepository;

@Service
public class ToDoService
{
	 @Autowired
	 private ToDoRepository toDoRepository;
	
     public List<ToDo> getAllToDoItems()
     {
    	  ArrayList<ToDo> todoList=new ArrayList<ToDo>();
    	  toDoRepository.findAll().forEach(todo->todoList.add(todo));
    	  return todoList;
     }
     
     public ToDo getToDoItemById(Long id)
     {
    	return  toDoRepository.findById(id).get();
     }
     
     public boolean  updatestatus(Long id)
     {
	  ToDo todo= getToDoItemById(id);
	       todo.setStatus("Completed");
	     return saveOrUpdateToDoItem(todo);
     }
     
     public boolean saveOrUpdateToDoItem(ToDo todo)
     {
	   ToDo updateObj=toDoRepository.save(todo);
	    if(getToDoItemById(updateObj.getId())!=null)
	    {
	    	return true;
	    }
	    return false;
     }
     
     public boolean deleteTodoItem(Long id)
     {
    	 toDoRepository.deleteById(id); 
   
    	 if(toDoRepository.findById(id).isEmpty())
    	 {
    		 return true;
    	 }
    	 return false;
     }
}
