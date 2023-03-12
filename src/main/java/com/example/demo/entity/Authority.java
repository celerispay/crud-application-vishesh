package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Authority {
    @Id
    private String id;
    private String name;
}
