package com.hescha.barbershop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Category extends AbstractEntity {

    @Column(length = 300)
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<MService> services = new ArrayList<MService>();

    @Override
    public String toString() {
        return name;
    }

}
