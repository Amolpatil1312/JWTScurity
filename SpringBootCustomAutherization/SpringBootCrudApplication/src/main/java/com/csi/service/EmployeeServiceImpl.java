package com.csi.service;

import com.csi.dao.EmployeeDao;
import com.csi.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeDao employeeDaoImpl;
    @Override
    public void signUp(Employee employee) {
        log.info("Trying to save the Data Into the DataBase...");
        employeeDaoImpl.save(employee);
    }

    @Override
    public void saveBulk(List<Employee> empList) {
        for(Employee employee : empList){
            employeeDaoImpl.save(employee);
        }
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {
        boolean flag = false;
        for(Employee employee:employeeDaoImpl.findAll()){
            if(employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public Employee updateById(Employee employee, int empId) {

        Employee employee1 = new Employee();
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpEmailId(employee1.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());
        return employeeDaoImpl.save(employee1);
    }

    @Override
    public List<Employee> findAll() {
        log.info("Trying to find All Data from Data Base...");
        return employeeDaoImpl.findAll();
    }

    public List<Employee> findAllByNative(){
        return employeeDaoImpl.findAllByNative();
    }
    @Override
    public Optional<Employee> findById(int empId) {
        log.info("Trying Find By ID...");
        return employeeDaoImpl.findById(empId);
    }

    @Override
    public List<Employee> findByName(String empName) {
        return employeeDaoImpl.findAll().stream().filter(emp->emp.getEmpName().equalsIgnoreCase(empName)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(int empId) {
        log.info("Trying to Delete The Data from Data Base...");
        employeeDaoImpl.deleteById(empId);
    }

    @Override
    public void deleteAll() {
        log.info("Trying to Delete All The Data From The Data Base...");
        employeeDaoImpl.deleteAll();
    }

    @Override
    public List<Employee> sortByName() {
        return employeeDaoImpl.findAll().stream().sorted(Comparator.comparing(Employee::getEmpName)).toList();
    }

    @Override
    public List<Employee> filterBySalary() {
        return employeeDaoImpl.findAll().stream().filter(emp->emp.getEmpSalary()>50000).toList();
    }
}
