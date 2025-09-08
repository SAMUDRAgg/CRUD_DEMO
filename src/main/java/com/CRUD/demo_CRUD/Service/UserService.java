package com.CRUD.demo_CRUD.Service;

import com.CRUD.demo_CRUD.Model.User;
import com.CRUD.demo_CRUD.Repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    private userRepo userRepo;
    public Optional<User> addUser(User user) {

        if(userRepo.findByUsername(user.getUsername()).isPresent()){
            return Optional.empty();
        }
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return Optional.of(userRepo.save(user));

    }


}
