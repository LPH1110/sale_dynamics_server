package com.pos.sale_dynamics.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "thumbnails")
public class Thumbnail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thumbnail_id")
    private long id;
    private String url;

    public Thumbnail() {
        this.url = "";
    }
    public Thumbnail(String url) {
        this.url = url;
    }
}
