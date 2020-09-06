package cl.pablosilvab.demobackendspringboot;


import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cl.pablosilvab.demobackendspringboot.service.GreetingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoBackendSpringBootApplicationTests {

	@Autowired
	private GreetingService greetingService;
	
	@Test
	public void saludarPersonaTest() {
		String retorno = greetingService.saludar("Pablo");
		assertTrue(retorno.equalsIgnoreCase(retorno));
	}

}
