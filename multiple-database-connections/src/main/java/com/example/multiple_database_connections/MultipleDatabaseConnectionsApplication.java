package com.example.multiple_database_connections;

import com.example.multiple_database_connections.studentRepository.StudentRepo;
import com.example.multiple_database_connections.student.Student;
import com.example.multiple_database_connections.teacher.Teacher;
import com.example.multiple_database_connections.techerRepository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultipleDatabaseConnectionsApplication implements CommandLineRunner {

	@Autowired
	StudentRepo studentRepo;
	@Autowired
	TeacherRepo teacherRepo;

	public static void main(String[] args) {
		SpringApplication.run(MultipleDatabaseConnectionsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Student student = new Student();
		student.setName("Aonkon Saha");
		student.setDepartment("CSE");
		student.setAddress("PUST");
		studentRepo.save(student);
		System.out.println(student);

		Teacher teacher = new Teacher();
		teacher.setName("BoloRam Saha");
		teacher.setDepartment("CSE");
		teacher.setAddress("PUST");
		teacherRepo.save(teacher);
		System.out.println(teacher);
	}
}
