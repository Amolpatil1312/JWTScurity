package com.csi.controller;

import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import com.csi.service.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController()
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/admin/signUp")
    public ResponseEntity<String> signUp(@Valid @RequestBody Employee employee){
        employeeServiceImpl.signUp(employee);
        log.info("##############Employee Data Saved successfully############");
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee Data Saved Successfully");
    }

    @PostMapping("/admin/saveBulk")
    public ResponseEntity<String> saveBulk(@RequestBody List<Employee> employeeList){
        employeeServiceImpl.saveBulk(employeeList);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee Data Saved Successfully Numbers "+employeeList.stream().count());
    }

    @RequestMapping(method = RequestMethod.GET,value = "/both/signIn/{empEmailId}/{empPassword}")
    public ResponseEntity<String> signIn(@PathVariable String empEmailId, @PathVariable String empPassword){
        String msg = "";
        if(employeeServiceImpl.signIn(empEmailId, empPassword)){
            msg = "Welcome to HRM Application";
        }else {
            msg = "Employee EmailId or Password may Incorrect please check Again...";
        }

        return ResponseEntity.ok(msg);

    }

    @GetMapping("/both/findAllNative")
    public ResponseEntity<List<Employee>> findAllNative(){
        return ResponseEntity.ok(employeeServiceImpl.findAllByNative());
    }
    @RequestMapping(method = RequestMethod.PUT,value = "/admin/updateById/{empId}")
    public ResponseEntity<Employee> updateById(@Valid @RequestBody Employee employee,@PathVariable int empId){
        return ResponseEntity.ok(employeeServiceImpl.updateById(employee, empId));
    }

    @RequestMapping(method = RequestMethod.GET,value = "/both/findAll")
    public ResponseEntity<List<Employee>> findAll(){
        log.info("Trying to Get all The Data from The Data Base....");
        return ResponseEntity.ok(employeeServiceImpl.findAll());
    }

    @RequestMapping(method = RequestMethod.GET,value = "/both/findById/{empId}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable int empId){
        log.info("Trying to Find By ID....");
        return ResponseEntity.ok(employeeServiceImpl.findById(empId));
    }

    @RequestMapping(value = "/both/findByName/{empName}",method =RequestMethod.GET )
    public ResponseEntity<List<Employee>> findByName(@PathVariable String empName){
        log.info("Trying to Get Data By ID");
        return ResponseEntity.ok(employeeServiceImpl.findByName(empName));
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/admin/deleteById/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId){
        employeeServiceImpl.deleteById(empId);
        log.info("Trying to Delete The Data By providing ID...");
        return ResponseEntity.ok("Employee Data Deleted Successfully");
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/admin/deleteAll")
    public ResponseEntity<String> deleteAll(){
        employeeServiceImpl.deleteAll();
        log.info("Trying to Delete All The Data..");
        return ResponseEntity.ok("All Data from the DataBase deleted Successfully...");
    }


}
