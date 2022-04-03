package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.model.Project;
import cl.pablosilvab.demobackendspringboot.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Project find(int id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project create(Project student) {
        Project saved = projectRepository.save(student);
        return saved;
    }

    @Override
    public Project update(int id, Project project) {
        Project projectById = this.find(id);
        if (projectById == null) return null;
        else {
            projectById.setName(project.getName());
            projectById.setUrl(project.getUrl());
            return projectRepository.save(projectById);
        }
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

}
