package com.rnaomix.itemmanagement.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UserForm {

    private Integer userId;

    @NonNull
    @Size(min = 1, max = 20)
    @NotEmpty(message = "ユーザ名を入力してください。")
    private String username;

    @NonNull
    @Size(min = 6, max = 20)
    @NotEmpty(message = "パスワードを入力してください。")
    private String password;

    @NonNull
    @Email(message = "正しいメールアドレスを入力してください。")
    private String email;

    @NonNull
    @Email(message = "正しいメールアドレスを入力してください。")
    private String emailConfirmation;

    @NonNull
    @Size(min = 1, max = 50)
    private String firstName;

    @NonNull
    @Size(min = 1, max = 50)
    private String lastName;

    private String authorization;

    public UserForm() {}

    public UserForm(Integer userId, String username, String email,
                    String firstName, String lastName, String authorization) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorization = authorization;
    }
}