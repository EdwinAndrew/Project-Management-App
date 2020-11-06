package com.example.pma.dao;

import com.example.pma.dao.ProjectRepository;
import com.example.pma.entities.Project;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql",
		"classpath:data.sql"}),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,scripts = {"classpath:drop.sql"})})
class ProjectManagementApplicationTests {
	@Autowired
	ProjectRepository proRepo;

	@Test
	public void ifNewProjectSaved_thenSuccess(){
		Project newProject = new Project("new test project", "COMPLETE","test description");
		proRepo.save(newProject);
		assertEquals(5,proRepo.findAll().size());
	}

}
