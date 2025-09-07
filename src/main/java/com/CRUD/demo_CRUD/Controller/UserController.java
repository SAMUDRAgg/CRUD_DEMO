package com.CRUD.demo_CRUD.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.CRUD.demo_CRUD.Model.User;
import com.CRUD.demo_CRUD.Service.UserService;
import com.CRUD.demo_CRUD.Service.jwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private jwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    ResponseEntity<String> userRegister(@RequestBody User user) {
        Optional<User> result = userService.addUser(user);
        if (result.isPresent()) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.badRequest().body("User already exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> userLogin(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUserName(),
                            user.getPassword()
                    )
            );

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(user.getUserName());

                Map<String, String> response = new HashMap<>();
                response.put("message", "Login Successful");
                response.put("token", token);

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
            }

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }
}
