package com.learning.management.controllers;

import com.learning.management.common.exceptions.NotFoundException;
import com.learning.management.models.TaskDTO;
import com.learning.management.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO) {
        try {
            taskService.saveTask(taskDTO);
        } catch (Exception e) {
            final Map<String, String> result = new HashMap<>();
            result.put("Error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        try {
            taskService.updateTask(id, taskDTO);
        } catch (NotFoundException e) {
            final Map<String, String> result = new HashMap<>();
            result.put("Error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
        } catch (NotFoundException e) {
            final Map<String, String> result = new HashMap<>();
            result.put("Error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
