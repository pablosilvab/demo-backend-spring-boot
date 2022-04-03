package cl.pablosilvab.demobackendspringboot.controller;

import cl.pablosilvab.demobackendspringboot.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cl.pablosilvab.demobackendspringboot.service.ProjectService;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {
	
	@Autowired
	ProjectService gretingService;
	
	@GetMapping("/{id}")
	public Project get(@PathVariable String id) {
		return gretingService.find(id);
	}
}
