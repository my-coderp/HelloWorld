package basic.com;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "HelloWorld"))
@SpringBootApplication
public class RestApplicationSql {

	public static void main(String[] args) {
		SpringApplication.run(RestApplicationSql.class, args);
	}


}
