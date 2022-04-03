package cl.pablosilvab.demobackendspringboot.controller;

import cl.pablosilvab.demobackendspringboot.model.Project;
import cl.pablosilvab.demobackendspringboot.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable String id) {
        try {
            int idValue = Integer.parseInt(id);
            Project foundProject = projectService.find(idValue);
            if (foundProject == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(foundProject);
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Project> create(@RequestBody Project student) {
        Project newProject = projectService.create(student);
        if (newProject == null) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(newProject, HttpStatus.CREATED);
        }
    }
}
