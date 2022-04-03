package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.model.Project;

public interface ProjectService {
	Project find(int id);
	Project create(Project student);
}
