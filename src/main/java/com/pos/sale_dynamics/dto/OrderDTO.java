package com.pos.sale_dynamics.dto;

public record OrderDTO(
        long id,
        int total,
        int received,
        int excess,

        boolean confirmed,

        String description
) {

    @Override
    public long id() {
        return id;
    }

    @Override
    public int total() {
        return total;
    }

    @Override
    public int received() {
        return received;
    }

    @Override
    public int excess() {
        return excess;
    }

    @Override
    public boolean confirmed() {
        return confirmed;
    }

    @Override
    public String description() {
        return description;
    }
}
