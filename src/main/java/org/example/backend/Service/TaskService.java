package org.example.backend.Service;

import org.example.backend.Entity.Tasks;
import org.example.backend.Repository.Task_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private Task_Repo repository;

    @Autowired
    public void Repository(Task_Repo repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<Tasks>> getAllTasks(){
        List<Tasks> tasks=repository.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    public ResponseEntity<Tasks> getTaskById(int id){
        Optional<Tasks> tasksOptional=repository.findById(id);
        return tasksOptional.map(tasks -> new ResponseEntity<>(tasks, HttpStatus.OK)).orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Tasks> createTask(Tasks task){
        Tasks newTask=repository.save(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    public ResponseEntity<Tasks> updateTask(int id, Tasks updateTask){
        Optional<Tasks> taskOptional=repository.findById(id);
        if(taskOptional.isPresent()){
            Tasks taskToUpdate=taskOptional.get();
            taskToUpdate.setTitle(updateTask.getTitle());
            taskToUpdate.setDescription(updateTask.getDescription());
            taskToUpdate.setDeadline(updateTask.getDeadline());
            repository.save(taskToUpdate);
            return new ResponseEntity<>(updateTask, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Void> deleteTask(int id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
