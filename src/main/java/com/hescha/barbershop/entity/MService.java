package com.hescha.barbershop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mservice")
@Data
public class MService extends AbstractEntity {

    @Column(length = 300)
    private String name;

    @Column(length = 500)
    private String description;

    private float price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "mservice", fetch = FetchType.LAZY)
    private List<Order> orderss = new ArrayList<Order>();

    @Override
    public String toString() {
        return name + "(" + category + ") - $" + price;
    }
}
