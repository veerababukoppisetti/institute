package com.being.institutemanagementsystem.features.data.model.experience.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration {
    private  int id;
    private String name;
    private String courseName;

    private  String instituteName;

    private String email;

    private  String contact;
    private  String location;

}
