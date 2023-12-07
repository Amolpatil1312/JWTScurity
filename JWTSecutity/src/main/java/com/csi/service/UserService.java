package com.csi.service;

import com.csi.exeption.RecordNotFoundExeption;
import com.csi.model.User;
import com.csi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepoImpl;

    public void signUp(User user){
        userRepoImpl.save(user);
    }

    public List<User> findAll(){
       return userRepoImpl.findAll();
    }

    public String signIn(String empEmailId,String empPassword){
        boolean flag = false;
        String stm = "";
        for (User user :findAll()){
            if(user.getUserEmailId().equals(empEmailId) && user.getUserPassword().equals(empPassword)){
                flag = true;
            }
        }
        if(flag){
            stm= "User signIn done Successfully";
        }else {
            stm = "User Email and Password is Not correct please Try Again";
        }
       return stm;
    }

    public Optional<User> findById(int empId){
        return Optional.ofNullable(userRepoImpl.findById(empId).orElseThrow(() -> new RecordNotFoundExeption("This Id is Not Exist")));
    }

    public Optional<User> findByName(String empName){
        return Optional.ofNullable(userRepoImpl.findByUserName(empName));
    }

    public void deleteById(int empName){
        userRepoImpl.deleteById(empName);
    }

    public void deleteAll(){
        userRepoImpl.deleteAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepoImpl.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), new ArrayList<>()) {
        };
    }
}
