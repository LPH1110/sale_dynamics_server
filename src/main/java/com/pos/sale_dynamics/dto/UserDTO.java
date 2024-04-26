package com.pos.sale_dynamics.dto;

public record UserDTO (
        String fullName,
        String email,
        String phone
) {
    @Override
    public String fullName() {
        return fullName;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String phone() {
        return phone;
    }
}
