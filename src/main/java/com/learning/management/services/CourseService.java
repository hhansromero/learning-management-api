package com.learning.management.services;

import com.learning.management.db.models.Course;
import com.learning.management.db.repositories.CourseRepository;
import com.learning.management.models.CourseDTO;
import com.learning.management.utils.DateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingRequestValueException;

import java.util.Objects;

@Slf4j
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void saveCourse(CourseDTO courseDTO) throws MissingRequestValueException {

        if (checkIfCourseNameExists(courseDTO.getName())) {
            throw new MissingRequestValueException("The course already exists.");
        }

        Course course = Course.builder()
                .name(courseDTO.getName())
                .startDate(DateConverter.strDateToZonedDateTime(courseDTO.getStartDate()))
                .build();
        courseRepository.save(course);
    }

    private boolean checkIfCourseNameExists(String name) {
        Course course = courseRepository.findByName(name)
                .orElse(null);
        return Objects.nonNull(course);
    }
}
