package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    // TODO: use any database

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

}
