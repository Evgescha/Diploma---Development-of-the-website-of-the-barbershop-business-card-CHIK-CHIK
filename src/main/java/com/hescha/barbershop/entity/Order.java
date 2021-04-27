package com.hescha.barbershop.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "myOrders")
@Data
public class Order extends AbstractEntity {

    private Date dateFrom;
    private Date dateTo;
    private Time timeTo;
    private boolean isPaid;
    private boolean isEnded;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mservice_id")
    private MService mservice;

    @Override
    public String toString() {
        return "Order [dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", " +
				"timeTo=" + timeTo + ", isPaid=" + isPaid
                + ", isEnded=" + isEnded + ", user=" + user + ", mservice=" + mservice + "]";
    }

}
