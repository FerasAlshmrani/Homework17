package com.example.homework17.Service;

import com.example.homework17.Model.User;
import com.example.homework17.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id ,User user){
        User newuser = userRepository.getById(id);

        if(newuser == null){
            return false;
        }
        newuser.setName(user.getName());
        newuser.setUsername(user.getUsername());
        newuser.setPassword(user.getPassword());
        newuser.setEmail(user.getEmail());
        newuser.setRole(user.getRole());
        newuser.setAge(user.getAge());
        userRepository.save(newuser);
        return true;

    }

    public Boolean deleteUser(Integer id){
        User user = userRepository.getById(id);

        if(user == null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }

}
