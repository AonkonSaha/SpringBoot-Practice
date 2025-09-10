package com.example.multiple_database_connections.studentRepository;

import com.example.multiple_database_connections.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
}
