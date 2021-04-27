package com.hescha.barbershop.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "myNews")
@Data
public class News extends AbstractEntity {

    @Column(length = 300)
    private String name;

    @Column(length = 300)
    private String image;

    @Column(length = 20000)
    private String text;

    private Date dates;

}
