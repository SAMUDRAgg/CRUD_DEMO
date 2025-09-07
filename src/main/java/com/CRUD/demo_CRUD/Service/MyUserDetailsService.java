package com.CRUD.demo_CRUD.Service;

import com.CRUD.demo_CRUD.Model.User;
import com.CRUD.demo_CRUD.Model.UserPrincipal;
import com.CRUD.demo_CRUD.Repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private userRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<User> user = userRepo.findByUserName(username);
        if (user.isPresent()) {

            return new UserPrincipal(user);
        } else {
            throw new UsernameNotFoundException("user not found");
        }
    }
}
