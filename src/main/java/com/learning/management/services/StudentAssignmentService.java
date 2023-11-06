package com.learning.management.services;

import com.learning.management.db.models.Course;
import com.learning.management.db.models.Student;
import com.learning.management.db.models.StudentAssignment;
import com.learning.management.db.repositories.StudentAssignmentRepository;
import com.learning.management.db.repositories.StudentRepository;
import com.learning.management.models.CourseDTO;
import com.learning.management.models.StudentAssignmentDTO;
import com.learning.management.models.StudentAssignmentListDTO;
import com.learning.management.models.StudentDTO;
import com.learning.management.utils.DateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingRequestValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class StudentAssignmentService {
    private final StudentAssignmentRepository studentAssignmentRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentAssignmentService(StudentAssignmentRepository studentAssignmentRepository,
                                    StudentRepository studentRepository) {
        this.studentAssignmentRepository = studentAssignmentRepository;
        this.studentRepository = studentRepository;
    }

    public List<StudentAssignmentListDTO> findAssignmentsByStudent(Long id) {

        List<StudentAssignmentListDTO> assignments =
                studentAssignmentRepository.findByStudentId(id).stream()
                        .map(s -> StudentAssignmentListDTO.builder()
                                .id(s.getId())
                                .student(StudentDTO.builder()
                                        .id(s.getStudent().getId())
                                        .firstName(s.getStudent().getFirstName())
                                        .lastName(s.getStudent().getLastName())
                                        .birthDate(s.getStudent().getBirthDate().toString())
                                        .ssnNumber(s.getStudent().getSsnNumber())
                                        .address(s.getStudent().getAddress())
                                        .email(s.getStudent().getEmail())
                                        .phone(s.getStudent().getPhone())
                                        .build())
                                .course(CourseDTO.builder()
                                        .id(s.getCourse().getId())
                                        .name(s.getCourse().getName())
                                        .startDate(s.getCourse().getStartDate().toString())
                                        .build())
                                .assignmentDate(s.getAssignmentDate().toString())
                                .build()).toList();
        return assignments;
    }

    public void createAssignmentsForAStudent(StudentAssignmentDTO studentAssignmentDTO)
            throws MissingRequestValueException {
        if (checkIfStudentExists(studentAssignmentDTO.getStudent().getId())) {
            throw new MissingRequestValueException(String.format("The student ID %s does not exist.",
                    studentAssignmentDTO.getStudent().getId()));
        }
        if (checkIfExceedsLimitOfCourses(studentAssignmentDTO.getCourses())) {
            throw new MissingRequestValueException("The limit for courses is 3.");
        }

        List<StudentAssignment> studentAssignments = new ArrayList<>();
        studentAssignmentDTO.getCourses().forEach(course ->
            studentAssignments.add(buildStudentAssignment(studentAssignmentDTO, course))
        );

        studentAssignmentRepository.saveAll(studentAssignments);
    }

    private boolean checkIfStudentExists(Long id) {
        Student student = studentRepository.findById(id)
                .orElse(null);
        return !Objects.nonNull(student);
    }

    private boolean checkIfExceedsLimitOfCourses(List<CourseDTO> courses) {
        return courses.size() > 3;
    }

    private StudentAssignment buildStudentAssignment(StudentAssignmentDTO studentAssignmentDTO,
                                                     CourseDTO courseDTO) {
        return StudentAssignment.builder()
                .student(Student.builder()
                        .id(studentAssignmentDTO.getStudent().getId())
                        .build())
                .course(Course.builder()
                        .id(courseDTO.getId())
                        .build())
                .assignmentDate(DateConverter.strDateToZonedDateTime(studentAssignmentDTO.getAssignmentDate()))
                .build();
    }
}
