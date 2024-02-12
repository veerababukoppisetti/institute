package com.being.institutemanagementsystem.features.data.model.experience.student;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



@SuperBuilder
@Data
@NoArgsConstructor
public class User {

    private  int id;
    private String username;
    private  String password;

    private String email;

}
