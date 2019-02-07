package com.raz.crud.entity;

import javax.persistence.*;

@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(generator = "DISCOUNT_SEQ_GEN")
    @SequenceGenerator(name = "DISCOUNT_SEQ_GEN", sequenceName = "DISCOUNT_SEQ", allocationSize = 10)
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
