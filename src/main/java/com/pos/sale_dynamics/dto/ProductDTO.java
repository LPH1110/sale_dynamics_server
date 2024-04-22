package com.pos.sale_dynamics.dto;


public record ProductDTO(
        String name,
        String description
) {
    public String description() {
        return description;
    }

    public String name() {
        return name;
    }
}
