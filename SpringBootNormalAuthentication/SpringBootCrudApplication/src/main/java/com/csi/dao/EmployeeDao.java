package com.csi.dao;

import com.csi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Queue;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    @Query(name = "FindAll",nativeQuery = true,value = "select * from employee")
    public List<Employee> findAllByNative();

    public Employee findByEmpName(String empName);


}
