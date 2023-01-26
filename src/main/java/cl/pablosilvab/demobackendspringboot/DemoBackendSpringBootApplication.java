package cl.pablosilvab.demobackendspringboot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
@OpenAPIDefinition(info = @Info(title = "Products API", version = "1.0", description = "Products Information"))
public class DemoBackendSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBackendSpringBootApplication.class, args);
	}

}
