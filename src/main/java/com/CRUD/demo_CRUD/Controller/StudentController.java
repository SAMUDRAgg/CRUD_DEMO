package com.CRUD.demo_CRUD.Controller;

import com.CRUD.demo_CRUD.Model.Student;
import com.CRUD.demo_CRUD.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // Create
    @PostMapping("/register")
    public ResponseEntity<Student> addStudents(@RequestBody Student student){
        return ResponseEntity.ok(studentService.addStudents(student));
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudents(@PathVariable Integer id ){
        return ResponseEntity.ok(studentService.getStudents(id));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudents(@PathVariable Integer id ){
        studentService.deleteStudents(id);
        return ResponseEntity.ok("Student deleted successfully!");
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudents(@RequestBody Student student , @PathVariable Integer id ){
        return ResponseEntity.ok(studentService.updateStudents(student, id));
    }
}
