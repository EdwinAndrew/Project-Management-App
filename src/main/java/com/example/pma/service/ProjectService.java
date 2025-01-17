package com.example.pma.service;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.dao.ProjectRepository;
import com.example.pma.dto.ChartData;
import com.example.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository proRepo;

    public Project save(Project project) {
        return proRepo.save(project);
    }

    public List<Project> getAll() {
        return proRepo.findAll();
    }

    public List<ChartData> getProjectStatus() {
        return proRepo.getProjectStatus();
    }

}
