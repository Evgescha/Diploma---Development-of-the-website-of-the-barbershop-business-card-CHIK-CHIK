package com.hescha.barbershop.entity;

import java.sql.Date;
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
@Table(name = "myUsers")
@Data
public class User extends AbstractEntity {

    private String fio;

    private Date dateBorn;

    private String email;

    private String phone;

    @Column(unique = true)
    private String username;

    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<Order>();

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
        super();
    }

    public List<String> getRoleListNames() {
        List<String> roleNames = new ArrayList<String>();
        roleNames.add(role.getName());
        return roleNames;
    }

    @Override
    public String toString() {
        return "User [fio=" + fio + ", dateBorn=" + dateBorn + ", email=" + email + ", phone=" + phone + ", username="
                + username + "]";
    }

}
