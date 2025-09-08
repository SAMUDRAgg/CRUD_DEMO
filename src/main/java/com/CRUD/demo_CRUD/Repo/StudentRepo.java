package com.CRUD.demo_CRUD.Repo;

import com.CRUD.demo_CRUD.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Integer> {
}
