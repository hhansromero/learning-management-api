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
public class StudentAssignmentListDTO {
    private Long id;
    private StudentDTO student;
    private CourseDTO course;
    private String assignmentDate;
}
