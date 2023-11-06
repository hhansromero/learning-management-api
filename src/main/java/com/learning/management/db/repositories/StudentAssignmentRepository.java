package com.learning.management.db.repositories;

import com.learning.management.db.models.StudentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAssignmentRepository extends JpaRepository<StudentAssignment, Long> {

    List<StudentAssignment> findByStudentId(Long id);
}
