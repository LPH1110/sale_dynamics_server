package com.pos.sale_dynamics.mapper;

import com.pos.sale_dynamics.domain.ApplicationUser;
import com.pos.sale_dynamics.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<ApplicationUser, UserDTO> {
    @Override
    public UserDTO apply(ApplicationUser user) {
        return new UserDTO(user.getFullName(), user.getUsername(), user.getEmail(), user.getPhone(), user.getAuthorities().stream().toList());
    }
}
