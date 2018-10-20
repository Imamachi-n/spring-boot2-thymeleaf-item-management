package com.rnaomix.itemmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Integer userId;

    @Column(length = 20, nullable = false, unique = true)
    @NotEmpty(message = "ユーザ名を入力してください。")
    private String username;

    @Column(nullable = false)
    @NotEmpty(message = "パスワードを入力してください。")
    private String password;

    @Column(nullable = false, unique = true)
    @Email(message = "正しいメールアドレスを入力してください。")
    @NotEmpty(message = "メールアドレスを入力してください。")
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User(){}

    public User(String username, String password, String email,
                String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
