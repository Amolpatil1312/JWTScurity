package com.csi.service;

import com.csi.dao.EmployeeDao;
import com.csi.model.CustomEmployeeDetails;
import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomEmployeeDetailService implements UserDetailsService {

    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public UserDetails loadUserByUsername(String empName) throws UsernameNotFoundException {
        Employee employee= this.employeeDao.findByEmpName(empName);
        System.out.println(employee);

        if(employee==null){
            throw new UsernameNotFoundException("NO EMPLOYEE Found");
        }
        return new CustomEmployeeDetails(employee);


    }
}
