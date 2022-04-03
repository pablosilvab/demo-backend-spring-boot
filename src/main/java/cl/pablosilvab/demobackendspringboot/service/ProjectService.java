package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.model.Project;

import java.util.ArrayList;
import java.util.List;

public interface ProjectService {
	Project find(int id);
	Project create(Project student);
	Project update(int idValue, Project project);
	List<Project> findAll();
}
