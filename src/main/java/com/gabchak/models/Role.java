package com.gabchak.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ROLE_NAME")
    private String roleName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USERS_TO_ROLES",
            joinColumns = {@JoinColumn(name = "FK_ROLE_ID")}, //{} - скобки можно убрать
            inverseJoinColumns = {@JoinColumn(name = "FK_USER_ID")})
    private Set<User> users = new HashSet<>();
}
