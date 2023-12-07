package com.csi.controller;

import com.csi.model.Authrequest;
import com.csi.model.User;
import com.csi.service.UserService;
import com.csi.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    UserService userServiceImpl;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody User user){
        userServiceImpl.signUp(user);
        return ResponseEntity.ok("SignUp Done Successfully");
    }

    @GetMapping("/signin/{UserEmailId}/{userPassword}")
    public ResponseEntity<String> signIn(@PathVariable String UserEmailId,@PathVariable String userPassword){
        return ResponseEntity.ok(userServiceImpl.signIn(UserEmailId, userPassword));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userServiceImpl.findAll());
    }

    @GetMapping("/findbyid/{userId}")
    public ResponseEntity<Optional<User>> findById(@PathVariable int userId){
        return ResponseEntity.ok(userServiceImpl.findById(userId));
    }

    @GetMapping("/findbyname/{userName}")
    public ResponseEntity<Optional<User>> finByName(@PathVariable String userName){
        return ResponseEntity.ok(userServiceImpl.findByName(userName));
    }

    @DeleteMapping("/deleteById/{userId}")
    public ResponseEntity<String> deleteById(@PathVariable int userId){
        userServiceImpl.deleteById(userId);
        return ResponseEntity.ok("Employee Data Deleted Successfully....");
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll(){
        userServiceImpl.deleteAll();
        return ResponseEntity.ok("All Data Deleted Successfully....");
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody Authrequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getUserPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Incorrect un/pwd");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
