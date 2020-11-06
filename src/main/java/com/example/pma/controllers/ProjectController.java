package com.example.pma.controllers;

import com.example.pma.entities.Employee;
import com.example.pma.entities.Project;
import com.example.pma.service.EmployeeService;
import com.example.pma.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService proService;

    @Autowired
    EmployeeService empService;

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = proService.getAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) { // Model defines a holder for model attributes
        Project aProject = new Project();
        List<Employee> employees = empService.getAll();
        model.addAttribute("project", aProject); //  add the supplied attribute under the supplied name.
        model.addAttribute("allEmployees", employees);
        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(Project project, Model model) {
        proService.save(project);

        // use redirect to prevent duplicate submissions
        return "redirect:/projects";
    }
}
