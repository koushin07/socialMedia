package com.socmed.socmed;

import com.socmed.socmed.role.Role;
import com.socmed.socmed.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocmedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocmedApplication.class, args);


	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository){

		return args -> {
			roleRepository.save(Role.builder().name("admin").build());
			roleRepository.save(Role.builder().name("normal").build());
		};
	}

}
