package com.pos.sale_dynamics.dto;

import com.pos.sale_dynamics.domain.ApplicationUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private ApplicationUser user;
    private String jwt;

    public LoginResponseDTO(ApplicationUser user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }
}
