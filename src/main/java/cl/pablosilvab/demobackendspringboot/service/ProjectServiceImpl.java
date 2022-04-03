package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Override
    public Project find(String id) {
        return new Project(
                1,
                "Demo Kubernetes",
                "https://github.com/pablosilvab/demo-kubernetes-examples");
    }

}
