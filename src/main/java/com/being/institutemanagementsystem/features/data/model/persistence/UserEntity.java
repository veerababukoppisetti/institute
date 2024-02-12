package com.being.institutemanagementsystem.features.data.model.persistence;

import javax.persistence.*;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Table(name = "registration")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private  int id;

    @Column(name = "name", nullable = false, length = 30)
    private  String username;

    @Column(name = "password")
    private  String password;


    @Column(name = "email", nullable = false, length = 20)
    private String email;

}
