package cl.pablosilvab.demobackendspringboot.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService{

	@Override
	public String saludar(String name) {
		return (name.equalsIgnoreCase("")) ? "Hello World" : "Hello "+ name;
	}

}
