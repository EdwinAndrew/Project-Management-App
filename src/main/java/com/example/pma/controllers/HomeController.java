package com.example.pma.controllers;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.dao.ProjectRepository;
import com.example.pma.dto.ChartData;
import com.example.pma.dto.EmployeeProject;
import com.example.pma.entities.Employee;
import com.example.pma.entities.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Value("${version}")
    private String ver;

    @Autowired
    ProjectRepository proRepo; // field injection
    @Autowired
    EmployeeRepository empRepo;
    /*
    <--------------------------------->
    this is constructor injection
    <--------------------------------->
    public EmployeeController(EmployeeRepository empRepo){
        this.empRepo = empRepo;
    }
    <--------------------------------->
    this is setter injection
    <--------------------------------->
    @Autowired
    public void setEmpRepo(EmployeeRepository empRepo){
        this.empRepo = empRepo;
    }
    <--------------------------------->

*/


    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {
        model.addAttribute("versionNumber",ver);
        Map<String, Object> map = new HashMap<>(); // holds column name and data type hence the object type.
        List<Project> projects = proRepo.findAll();
        model.addAttribute("projectsList", projects);
        List<ChartData> projectData = proRepo.getProjectStatus();
        // Convert projectData object into json struct for use in JS.
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        // [["NOTSTARTED",1],["INPROGRESS",2],["COMPLETED",1]]

        model.addAttribute("projectStatusCnt",jsonString);

        List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
        model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);
        return "main/home";
    }
}
