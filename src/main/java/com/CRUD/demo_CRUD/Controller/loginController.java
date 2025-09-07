package com.CRUD.demo_CRUD.Controller;

import com.CRUD.demo_CRUD.Model.User;
import com.CRUD.demo_CRUD.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class loginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    ResponseEntity<String> userRegister(@RequestBody User user) {
        Optional<User> result = userService.addUser(user);
        if (result.isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("login")
    public ResponseEntity<String> userLogin(@RequestBody User user) {
//        boolean success = userService.validateUser(user);
//        if(success){
//               return ResponseEntity.ok("Login Successful");
//        }else{
//            return  ResponseEntity.status(401).body("Invalid credentials");
//        }

        try {
            Authentication authentication = authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(
                            user.getUserName(),
                            user.getPassword()
                    )
            );
            return ResponseEntity.ok("Login Successful");

        }
        catch ( AuthenticationException e){
            return  ResponseEntity.status(401).body("Invalid credentials");
        }
    }

}
