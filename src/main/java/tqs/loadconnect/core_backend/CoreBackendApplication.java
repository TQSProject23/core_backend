package tqs.loadconnect.core_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }) // Disable Spring Security
public class CoreBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreBackendApplication.class, args);
	}

}
