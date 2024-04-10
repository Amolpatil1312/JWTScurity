package com.csi.service.test;

import com.csi.dao.EmployeeDao;
import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @MockBean
    EmployeeDao employeeDaoImpl;

    @Test
    public void findAllTest(){
        Mockito.when(employeeDaoImpl.findAll()).thenReturn(Stream.of(new Employee(101,"Nitin","Jalgaon",450000,8745452184l,"nitin@gmail.com","nitin@123","ROLE_ADMIN")).toList());
        Assertions.assertEquals(1,employeeService.findAll().size());
    }

    @Test
    public void findByIdTest(){
        Employee employee = new Employee(101,"jadhav","nigdi",78000,7865646441l,"jadhav@gmail.com","jadhav@123","ROLE_NORMAL");

            employeeService.findById(employee.getEmpId());

            Mockito.verify(employeeDaoImpl,Mockito.times(1)).findById(employee.getEmpId());
    }

    @Test
    public void deleteByIdTest(){
        Employee employee = new Employee(101,"jadhav","nigdi",78000,7865646441l,"jadhav@gmail.com","jadhav@123","ROLE_NORMAL");
        employeeService.deleteById(employee.getEmpId());

        Mockito.verify(employeeDaoImpl,Mockito.times(1)).deleteById(employee.getEmpId());
    }

    @Test
            public void deleteAll(){
        Employee employee = new Employee(101,"jadhav","nigdi",78000,7865646441l,"jadhav@gmail.com","jadhav@123","ROLE_NORMAL");

        employeeService.deleteAll();

        Mockito.verify(employeeDaoImpl,Mockito.times(1)).deleteAll();
    }


}
