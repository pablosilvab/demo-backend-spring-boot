package cl.pablosilvab.demobackendspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/greeting")
	public String sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "Hello "+ name;
	}
}
