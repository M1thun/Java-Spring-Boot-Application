package com.project.pma.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "project_seq")
    @SequenceGenerator(name="project_seq",sequenceName="project_seq", allocationSize = 1)

    private long projectId;

    private String name;
    private String stage;
    private String description;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    //@OneToMany(mappedBy = "project")
    @NotBlank(message="date cannot be empty")
    private Date startDate;

    @NotBlank(message="date cannot be empty")
    private Date endDate;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},
            fetch=FetchType.LAZY)
    @JoinTable(name="project_employee",
            joinColumns = @JoinColumn(name="project_id"),
            inverseJoinColumns = @JoinColumn(name="employee_id"))
    @JsonIgnore
    private List<Employee> emps;

    public Project() {

    }

    public Project(String name, String stage, String description) {
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addEmployee(Employee emp){
        if(emps==null){
            emps=new ArrayList<>();
        }
        emps.add(emp);
    }
}
