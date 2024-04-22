package com.pos.sale_dynamics.repository;

import com.pos.sale_dynamics.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String username);
    List<ApplicationUser> findAll();

}
