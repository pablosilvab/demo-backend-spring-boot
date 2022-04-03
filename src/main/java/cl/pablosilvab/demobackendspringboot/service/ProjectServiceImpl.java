package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    // TODO: use any database. project id = 0 for simulate that a project doesn't exists

    @Override
    public Project find(int id) {
        if (id == 0) return null;
        return new Project(
                1,
                "Demo Kubernetes",
                "https://github.com/pablosilvab/demo-kubernetes-examples");
    }

    @Override
    public Project create(Project student) {
        return student;
    }

    @Override
    public Project update(int id, Project project) {
        Project projectById = this.find(id);
        if (projectById == null) return null;
        else {
            projectById.setName(project.getName());
            projectById.setUrl(project.getUrl());
            return projectById;
        }
    }

}
