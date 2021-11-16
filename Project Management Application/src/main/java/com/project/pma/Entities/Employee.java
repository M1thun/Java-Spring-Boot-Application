package com.project.pma.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.pma.validators.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "employee_seq")
    @SequenceGenerator(name="employee_seq",sequenceName="employee_seq", allocationSize = 1)
    public long empId;


    @NotNull
    @Size(min=2, max=50)
    private String fname;

    @NotBlank(message = "Must give last name")
    @Size(min=2, max=50)
    private String lname;

    //@Column(unique=true)
    @Email(message = "Enter valid email")
    @NotBlank(message = "Must give email")
    @UniqueValue
    private String email;


//    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},
//    fetch=FetchType.LAZY)
//    @JoinColumn(name="project_id")




    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},
            fetch=FetchType.LAZY)
    @JoinTable(name="project_employee",
            joinColumns = @JoinColumn(name="employee_id"),
            inverseJoinColumns = @JoinColumn(name="project_id"))
    @JsonIgnore
    private List<Project> project;

    public Employee(){

    }

    public Employee(String fname, String lname, String email) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
//    public Project getProject() {
//        return project;
//    }
//
//    public void setProject(Project project) {
//        this.project = project;
//    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }
    public void setProject(List<Project> project) {
        this.project = project;
    }

    public List<Project> getProject() {
        return project;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
