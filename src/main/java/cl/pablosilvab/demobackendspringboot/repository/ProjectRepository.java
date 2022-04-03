package cl.pablosilvab.demobackendspringboot.repository;

import cl.pablosilvab.demobackendspringboot.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
