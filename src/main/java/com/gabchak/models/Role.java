package com.gabchak.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ROLE_NAME")
    private String roleName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USERS_TO_ROLES",
            joinColumns = {@JoinColumn(name = "FK_ROLE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "FK_USER_ID")})
    private Set<User> users;
}
