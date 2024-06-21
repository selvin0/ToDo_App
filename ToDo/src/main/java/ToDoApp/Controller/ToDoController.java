package ToDoApp.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ToDoApp.Model.ToDo;
import ToDoApp.Service.ToDoService;

@Controller
public class ToDoController
{ 
	 @Autowired
     private ToDoService service;
	 
	 @GetMapping({"/","viewToDoList"})
	 public String viewAllToDoItems(Model model,@ModelAttribute("message") String message)
	 {
		model.addAttribute("list",service.getAllToDoItems());
		model.addAttribute("msg",message);
		return "ViewToDoList";
	 }
	 
	 @GetMapping("/updateToDoStatus/{id}")
	 public String updateToDoStatus(@PathVariable Long id,RedirectAttributes redirectAttributes)
	 {
		 if(service.updatestatus(id))
		 {
			 redirectAttributes.addFlashAttribute("message","update Success");
			 return "redirect:/viewToDoList";
		 }
		 redirectAttributes.addFlashAttribute("message","update Failure");
		 return "redirect:/viewToDoList";
	 }
	 
	 @GetMapping("/addToDoItem")
	 public String addToDoItem(Model model)
	 {
		 model.addAttribute("todo",new ToDo());
		 return "AddToDoItem";
	 }
	 
	 @PostMapping("/saveToDoItem")
	 public String saveToDoItems(ToDo todo,RedirectAttributes redirectAttributes)
	 {
		 if(service.saveOrUpdateToDoItem(todo))
		 {
			 redirectAttributes.addFlashAttribute("message","save Success"); 
			 return "redirect:/viewToDoList";
		 }
		 redirectAttributes.addFlashAttribute("message","save Failure");
		 return "redirect:/AddToDoItem";
	 }
	 
	 @GetMapping("/editToDoItem/{id}")
	 public String editToDoItem(@PathVariable("id") long id,Model model)
	 {
		  model.addAttribute("todo",service.getToDoItemById(id));
		  return "EditToDoItem";
	 }
	 
	 @PostMapping("/editSaveToDoItem")
	 public String editSaveToDoItem(ToDo todo,RedirectAttributes redirectAttributes)
	 {
		 if(service.saveOrUpdateToDoItem(todo))
		 {
			 redirectAttributes.addFlashAttribute("message","Edit Success"); 
			 return "redirect:/viewToDoList";
		 }
		 redirectAttributes.addFlashAttribute("message","Edit Failure");
		 return "redirect:/editToDoItem/"+todo.getId();
	 }
	 
	 @GetMapping("/deleteToDoItem/{id}")
	 public String deleteToDoItem(@PathVariable("id") Long id,RedirectAttributes redirectAttributes)
	 {
		   if(service.deleteTodoItem(id))
		   {
			   redirectAttributes.addFlashAttribute("message","Delete Success"); 
			   return "redirect:/viewToDoList";
		   }
		   redirectAttributes.addFlashAttribute("message","Delete failure");
		   return "redirect:/viewToDoList";
	 }
}
