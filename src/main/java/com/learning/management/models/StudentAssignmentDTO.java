package com.learning.management.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAssignmentDTO {
    private Long id;
    private StudentDTO student;
    private List<CourseDTO> courses;
    private String assignmentDate;
}
