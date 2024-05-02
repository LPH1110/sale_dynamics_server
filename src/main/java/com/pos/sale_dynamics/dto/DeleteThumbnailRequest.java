package com.pos.sale_dynamics.dto;

public record DeleteThumbnailRequest(
        String barcode,
        long thumbnailId
) {
    @Override
    public String barcode() {
        return barcode;
    }

    @Override
    public long thumbnailId() {
        return thumbnailId;
    }
}
