package com.example.pma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //hibernate is managing the ID incrementation.
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(name = "project_seq",
            sequenceName = "project_seq",
            allocationSize = 1)
    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    /* cascade allows us to apply the same rules from the parent entity to the children. Merge will merge the
    children if the parent merges with another project. Refresh refreshes projects and employees. Persist saves the
    parent and children. Detach means that it will detach all associated entities from the hibernate session. Fetch
    type lazy only loads the entity but not the children. Eager is used to load both entity and children. Note that
    the performance will be affected (slowed) if the eager approach is used.*/
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST,},
            fetch = FetchType.LAZY)
    /* Join table to combine the project and employee tables together*/
    @JoinTable(name="project_employee",
            joinColumns = @JoinColumn(name="employee_id"),
            inverseJoinColumns = @JoinColumn(name="project_id")
    )
    @JsonIgnore
    private List<Project> projects;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
