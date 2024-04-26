package com.pos.sale_dynamics.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;

    public Tag() {
        this.name = "";
        this.property = null;
    }

    public Tag(String name) {
        this.name = name;
    }

    public void setProperty(Property property) {
        if (property == null) {
            if (this.property != null) {
                this.property.getTags().remove(this);
            }
        } else {
            property.getTags().add(this);
        }

        this.property = property;
    }
}
