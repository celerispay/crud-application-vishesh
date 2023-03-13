package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import com.example.demo.utility.Message;

@Entity
@Getter
@Setter
public class User {
    @Id
    private String id;

    @NotBlank(message = Message.USERNAME_NOT_BLANK)
    private String username;
   
    @NotBlank(message = Message.PASSWORD_NOT_BLANK)
    private String password;

    @NotBlank(message = Message.EMAIL_NOT_BLANK)
    @Email(message = Message.INVALID_EMAIL)
    private String email;

    @ManyToMany
    private List<Authority> authorities;
}
