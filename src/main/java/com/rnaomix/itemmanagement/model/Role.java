package com.rnaomix.itemmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@Table(name = "role")
public class Role {

    public enum RoleName {
        USER, MANAGER, ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "role")
    private RoleName role;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role(RoleName role) {
        this.role = role;
    }
}
