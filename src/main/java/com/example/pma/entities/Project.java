package com.example.pma.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*Entities, by nature of their unique identifier, exist independently of other objects whereas values do not.
Entities are domain model classes which correlate to rows in a database table, using a unique identifier.*/
@Entity
public class Project {
    // the class structure will map to the DB.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(name = "project_seq",
    sequenceName = "project_seq",
    allocationSize = 1)
    private long projectId;
    private String name;
    private String stage; // lifecycle of project(NOTSTARTED,COMPLETED,INPROGRESS)
    private String description;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST,},
            fetch = FetchType.LAZY)
    @JoinTable(name="project_employee",
            joinColumns = @JoinColumn(name="project_id"),
            inverseJoinColumns = @JoinColumn(name="employee_id")
    )
    @JsonIgnore
    private List<Employee> employees;

    public Project() {
    }

    public Project(String name, String stage, String description) {
        super();
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    //convenience method:
    public void addEmployee(Employee emp){
        if (employees == null){
            employees = new ArrayList<>();
        }
        employees.add(emp);
    }

}
