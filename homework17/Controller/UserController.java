package com.example.homework17.Controller;

import com.example.homework17.ApiResponse.ApiResponse;
import com.example.homework17.Model.User;
import com.example.homework17.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User Added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTodo(@RequestBody @Valid User user, @PathVariable Integer id,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdated =userService.updateUser(id,user);

        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("User Updated"));
        }
        return ResponseEntity.status(400).body("Wrong id");

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTodo(@PathVariable Integer id){
        Boolean isDeleted =userService.deleteUser(id);

        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("User Deleted"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("Wrong id "));

    }

}
