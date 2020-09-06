package cl.pablosilvab.demobackendspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.pablosilvab.demobackendspringboot.service.GreetingService;

@RestController
public class HomeController {
	
	@Autowired
	GreetingService gretingService;
	
	@GetMapping("/greeting")
	public String sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return gretingService.saludar(name);
	}
}
