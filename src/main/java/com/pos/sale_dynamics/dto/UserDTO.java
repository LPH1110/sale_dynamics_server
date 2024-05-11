package com.pos.sale_dynamics.dto;

import com.pos.sale_dynamics.domain.Role;

import java.util.List;

public record UserDTO (
        String fullName,
        String username,
        String email,
        String phone,
        List<Role> authorities,
        List<OrderDTO> orders
) {
    @Override
    public String fullName() {
        return fullName;
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String phone() {
        return phone;
    }

    @Override
    public List<Role> authorities() {
        return authorities;
    }

    @Override
    public List<OrderDTO> orders() {
        return orders;
    }
}
