package com.example.task_microservice.controller;

import com.example.task_microservice.data.Task;
import com.example.task_microservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "http://localhost:8080")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<String> addTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.addTask(task));
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable String id, @RequestBody Task task) {
        String response = taskService.updateTask(id, task);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable String id) {
        String response = taskService.deleteTask(id);
        return ResponseEntity.ok(response);
    }
}

