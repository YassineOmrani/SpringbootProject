package com.yassine.app;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.yassine.app.controllers.AgenceController;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpringbootProjectApplication {

	public static void main(String[] args) {
		new File(AgenceController.uploadDirectory).mkdir();
		SpringApplication.run(SpringbootProjectApplication.class, args);
	}

}
