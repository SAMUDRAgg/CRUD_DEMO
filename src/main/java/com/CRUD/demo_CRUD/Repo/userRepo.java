package com.CRUD.demo_CRUD.Repo;

import com.CRUD.demo_CRUD.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepo extends JpaRepository<User,String> {

    Optional<User> findByUserName(String userName);
}
