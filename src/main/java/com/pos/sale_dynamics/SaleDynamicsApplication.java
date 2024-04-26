package com.pos.sale_dynamics;

import com.pos.sale_dynamics.domain.ApplicationUser;
import com.pos.sale_dynamics.domain.Role;
import com.pos.sale_dynamics.repository.RoleRepository;
import com.pos.sale_dynamics.repository.UserRepository;
import com.pos.sale_dynamics.service.AuthenticationService.AuthenticationService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class SaleDynamicsApplication implements CommandLineRunner {
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UserRepository userRepository;



	@Override
	public void run(String... args) throws Exception {
		Role adminRole = null;
		if (roleRepository.findByAuthority("ADMIN").isEmpty()) {
			adminRole = roleRepository.save(new Role("ADMIN"));
		}

		if (roleRepository.findByAuthority("USER").isEmpty()) {
			roleRepository.save(new Role("USER"));
		}


		if(userRepository.findByUsername("admin").isEmpty()) {
			ApplicationUser user = authenticationService.registerUser("admin", "admin");
			user.getAuthorities().add(adminRole);
			userRepository.save(user);
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(SaleDynamicsApplication.class, args);
	}


}
