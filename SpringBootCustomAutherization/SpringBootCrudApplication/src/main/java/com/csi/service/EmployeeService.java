package com.csi.service;

import com.csi.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public void signUp(Employee employee);

    public void saveBulk(List<Employee> empList);

    public boolean signIn(String empEmailId,String empPassword);

    public Employee updateById(Employee employee,int empId);

    public List<Employee> findAll();

    public Optional<Employee> findById(int empId);

    public List<Employee> findByName(String empName);

    public void deleteById(int empId);

    public void deleteAll();

    public List<Employee> sortByName();

    public List<Employee> filterBySalary();


}
