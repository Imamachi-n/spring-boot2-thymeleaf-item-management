package com.rnaomix.itemmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Table(name = "role")
public class Role {

    public enum RoleName {
        USER, ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "role")
    private RoleName role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(RoleName role) {
        this.role = role;
    }
}
