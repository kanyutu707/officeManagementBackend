package org.example.backend.Controller;

import org.example.backend.Entity.Tasks;
import org.example.backend.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/smartEmployer/tasks")
public class TaskController {
    private TaskService service;

    @Autowired
    public void Service(TaskService service) {
        this.service = service;
    }
    @GetMapping("/get_all")
    public ResponseEntity<List<Tasks>> getAllTasks(){
        return service.getAllTasks();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Tasks> getTaskById(@PathVariable int id){
        return service.getTaskById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Tasks> createTask(@RequestBody Tasks task){
        return service.createTask(task);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Tasks> updateTask(@PathVariable int id, @RequestBody Tasks task){
        return service.updateTask(id, task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id){
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
