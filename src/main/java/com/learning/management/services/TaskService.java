package com.learning.management.services;

import com.learning.management.common.exceptions.NotFoundException;
import com.learning.management.db.models.Category;
import com.learning.management.db.models.StudentAssignment;
import com.learning.management.db.models.Task;
import com.learning.management.db.repositories.TaskRepository;
import com.learning.management.models.TaskDTO;
import com.learning.management.utils.DateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void saveTask(TaskDTO taskDTO) {
        Task task = buildTask(taskDTO);
        taskRepository.save(task);
    }

    public void updateTask(Long id, TaskDTO taskDTO) {
        Task existingTask = getExistingTask(id);

        existingTask.setTaskDate(DateConverter.strDateToZonedDateTime(taskDTO.getTaskDate()));
        existingTask.setHours(taskDTO.getHours());
        existingTask.setCategory(Category.builder()
                .id(taskDTO.getCategory().getId())
                .build());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setStudentAssignment(StudentAssignment.builder()
                .id(taskDTO.getStudentAssignment().getId())
                .build());
        taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        Task existingTask = getExistingTask(id);
        taskRepository.delete(existingTask);
    }

    private Task buildTask(TaskDTO taskDTO) {
        return Task.builder()
                .taskDate(DateConverter.strDateToZonedDateTime(taskDTO.getTaskDate()))
                .hours(taskDTO.getHours())
                .category(Category.builder()
                        .id(taskDTO.getCategory().getId())
                        .build())
                .description(taskDTO.getDescription())
                .studentAssignment(StudentAssignment.builder()
                        .id(taskDTO.getStudentAssignment().getId())
                        .build())
                .build();
    }

    private Task getExistingTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(NotFoundException::taskNotFound);
    }
}
