package com.raz.crud.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "discount")
@NamedQueries(
        @NamedQuery(name = Discount.QUERY_SELECT_BY_NAME, query = "select d from Discount d where d.name = :" + Discount.PARAM_NAME)
)
public class Discount {

    public static final String QUERY_SELECT_BY_NAME = "Discount.selectByName";
    public static final String PARAM_NAME = "name";

    @Id
    @GeneratedValue(generator = "DISCOUNT_SEQ_GEN")
    @SequenceGenerator(name = "DISCOUNT_SEQ_GEN", sequenceName = "DISCOUNT_SEQ", allocationSize = 10)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private Date creationDate;

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
