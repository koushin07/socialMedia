package com.socmed.socmed;

import com.socmed.socmed.modules.role.Role;
import com.socmed.socmed.modules.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SocmedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocmedApplication.class, args);


	}
	@GetMapping("/testing")
	public String tst(){
		return "successfully deployed";
	}

//	@Bean
//	CommandLineRunner run(RoleRepository roleRepository){
//
//		return args -> {
//			roleRepository.save(Role.builder().name("admin").build());
//			roleRepository.save(Role.builder().name("normal").build());
//		};
//	}

}
