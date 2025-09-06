package com.CRUD.demo_CRUD.Service;

import com.CRUD.demo_CRUD.DAO.Student;
import com.CRUD.demo_CRUD.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    // Create
    public Student addStudents(Student student) {
        return studentRepo.save(student);
    }

    // Read
    public Student getStudents(Integer id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // Delete
    public void deleteStudents(Integer id) {
        if(studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
        } else {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }

    // Update
    public Student updateStudents(Student student, Integer id) {
        Optional<Student> existingStudent = studentRepo.findById(id);
        if(existingStudent.isPresent()) {
            Student s = existingStudent.get();
            s.setName(student.getName());   // assuming Student has 'name'
            s.setBranch(student.getBranch());
            // set other fields here...
            return studentRepo.save(s);
        } else {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }
}
