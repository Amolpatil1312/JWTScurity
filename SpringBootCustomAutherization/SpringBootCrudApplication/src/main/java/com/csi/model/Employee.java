package com.csi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.naming.Name;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int empId;

    @Column(name = "Name",nullable = false)
    @Size(min = 4, message = "Employee Name must be 4 chars Atleast")
    private String empName;

    @Column(name = "Address", nullable = false)
    @Size(min = 4,message = "Employee Address Must be 4 Chars Atleast")
    private String empAddress;

    @Column(name = "Salary")
    private double empSalary;

    @Column(name = "ContactNumber")
    @Range(max = 9999999999l,min = 1000000000l, message = "Employee Contact Must be 10 Digits")
    private long empContactNumber;

    @Column(name = "EmailId")
    @Email(message = "Email Id Should be Valid")
    private String empEmailId;

    @Column(name = "Password")
    @Size(min = 4, message = "Password Must be 4 Chars atleast")
    private String empPassword;

    @Column(name = "Role")
    private String role;







}
