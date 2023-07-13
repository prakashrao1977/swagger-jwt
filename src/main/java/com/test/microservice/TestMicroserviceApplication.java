package com.test.microservice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
@EnableSwagger2
@CrossOrigin("*")
public class TestMicroserviceApplication implements CommandLineRunner {
    // https://github.com/koldaman/springboot-jwt-swagger/blob/master/pom.xml
    // https://www.youtube.com/watch?v=kE_iN9eFYyw
    // https://www.youtube.com/watch?v=kE_iN9eFYyw
    // https://www.youtube.com/watch?v=P_29bHsVjjg
    // https://www.youtube.com/watch?v=P_29bHsVjjg
    // https://github.com/koldaman/springboot-jwt-swagger/blob/master/src/main/java/cz/e23/config/WebSecurityConfig.java
    // https://stackoverflow.com/questions/61477056/why-is-the-authorization-header-missing-in-requests-sent-from-swagger-ui
    // https://github.com/springfox/springfox/issues/2194
        
    @Autowired
    private PasswordEncoder passwordEncoder;
        
	public static void main(String[] args) {
		SpringApplication.run(TestMicroserviceApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper ()
	{
	    return new ModelMapper();
	}
	
	public void run (String ...args) throws Exception 
	{
	    System.out.println(this.passwordEncoder.encode("xyz"));
	}
}
