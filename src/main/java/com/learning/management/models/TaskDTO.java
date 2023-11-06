package com.learning.management.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String taskDate;
    private String hours;
    private String description;
    private CategoryDTO category;
    private StudentAssignmentDTO studentAssignment;
}
