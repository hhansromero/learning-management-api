package com.learning.management.services;

import com.learning.management.db.models.Student;
import com.learning.management.db.repositories.StudentRepository;
import com.learning.management.models.StudentDTO;
import com.learning.management.utils.DateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingRequestValueException;

import java.util.Objects;

@Slf4j
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(StudentDTO studentDTO) throws MissingRequestValueException {

        if (checkIfEmailAlreadyExists(studentDTO.getEmail())) {
            throw new MissingRequestValueException(
                    String.format("The Email address used %s already exists in the system.",
                            studentDTO.getEmail())
            );
        }

        Student student = Student.builder()
                .ssnNumber(studentDTO.getSsnNumber())
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .birthDate(DateConverter.strDateToZonedDateTime(studentDTO.getBirthDate()))
                .address(studentDTO.getAddress())
                .email(studentDTO.getEmail())
                .phone(studentDTO.getPhone())
                .build();
        studentRepository.save(student);
    }

    private boolean checkIfEmailAlreadyExists(String email) {
        Student student = studentRepository.findByEmailIgnoreCase(email)
                .orElse(null);
        return Objects.nonNull(student);
    }
}
