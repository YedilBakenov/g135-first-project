package kz.project.G135;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class G135Application {

	public static void main(String[] args) {
		SpringApplication.run(G135Application.class, args);
	}

}
