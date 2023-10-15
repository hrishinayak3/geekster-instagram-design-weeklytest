package com.geekster.InstagramClone.controller;

import com.geekster.InstagramClone.dto.SignInInput;
import com.geekster.InstagramClone.dto.SignInOutput;
import com.geekster.InstagramClone.dto.SignUpInput;
import com.geekster.InstagramClone.dto.SignUpOutput;
import com.geekster.InstagramClone.model.User;
import com.geekster.InstagramClone.service.TokenService;
import com.geekster.InstagramClone.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    TokenService authService;

    @PostMapping("/signup")
    public SignUpOutput signUp(@RequestBody SignUpInput signUpDto) {
        return userService.signup(signUpDto);
    }

    @PostMapping("/signin")
    public SignInOutput signin (@Valid @RequestBody SignInInput signInDto) {
        return userService.signin(signInDto);
    }

    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestParam String email, @RequestParam String token, @RequestBody User user) {
        HttpStatus status;
        String message = null;
        if(authService.authenticate(email,token))
        {
            try {
                userService.updateUser(user, token);
                status = HttpStatus.OK;
            } catch (Exception e) {
                message = "invalid information";
                status = HttpStatus.BAD_REQUEST;

            }

        }
        else
        {
            status = HttpStatus.FORBIDDEN;
        }



        return new ResponseEntity<String>(message,status);
    }


    @DeleteMapping("/signout")
    public ResponseEntity<String> signout(@RequestParam String email, @RequestParam String token) {
        HttpStatus status;
        String message = null;
        if(authService.authenticate(email,token))
        {
            authService.deleteToken(token);
            status = HttpStatus.OK;
            message = "Sign out successful";

        }
        else
        {
            status = HttpStatus.FORBIDDEN;
        }



        return new ResponseEntity<String>(message,status);
    }
}
