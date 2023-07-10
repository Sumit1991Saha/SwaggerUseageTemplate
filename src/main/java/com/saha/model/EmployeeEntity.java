package com.saha.model;

import com.saha.rest.model.Employee;
import com.saha.rest.model.EmployeeConfiguration;
import com.saha.rest.model.EmployeeType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    @Length(min=2, max=255)
    private String firstName;

    @Column(name = "lastname")
    @Length(min=2, max=255)
    private String lastName;

    @Column(name = "designation")
    @Length(min=2, max=255)
    private String designation;

    @Column(name = "emp_type")
    private String employeeType;

    public EmployeeEntity(){
    }
 
    public EmployeeEntity(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(designation, that.designation) && employeeType == that.employeeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, designation, employeeType);
    }

    public Employee toDTO() {
        Employee employee = new Employee();
        employee.setId(Long.valueOf(this.getId()));
        employee.setFirstName(this.getFirstName());
        employee.setLastName(this.getLastName());
        employee.setDesignation(this.getDesignation());
        EmployeeConfiguration empConfig = new EmployeeConfiguration();
        empConfig.setEmployeeType(EmployeeType.fromValue(this.getEmployeeType()));
        employee.setEmployeeConfiguration(empConfig);
        return employee;
    }

    public void fromDTO(Employee employee) {
        this.setFirstName(employee.getFirstName());
        this.setLastName(employee.getLastName());
        this.setDesignation(employee.getDesignation());
        this.setEmployeeType(employee.getEmployeeConfiguration().getEmployeeType().toString());
    }
}
