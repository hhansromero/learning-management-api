package com.learning.management.controllers;

import com.learning.management.models.StudentAssignmentDTO;
import com.learning.management.models.StudentAssignmentListDTO;
import com.learning.management.services.StudentAssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class StudentAssignmentsController {

    private final StudentAssignmentService studentAssignmentService;

    @Autowired
    public StudentAssignmentsController(StudentAssignmentService studentAssignmentService) {
        this.studentAssignmentService = studentAssignmentService;
    }

    @GetMapping("/student/{id}/student-assignments")
    public ResponseEntity<List<StudentAssignmentListDTO>> findAssignmentsByStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentAssignmentService.findAssignmentsByStudent(id));
    }

    @PostMapping("/student-assignments")
    public ResponseEntity<?> createAssignmentsForAStudent(@RequestBody StudentAssignmentDTO studentAssignmentDTO) {
        try {
            studentAssignmentService.createAssignmentsForAStudent(studentAssignmentDTO);
        } catch (MissingRequestValueException e) {
            final Map<String, String> result = new HashMap<>();
            result.put("Error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
