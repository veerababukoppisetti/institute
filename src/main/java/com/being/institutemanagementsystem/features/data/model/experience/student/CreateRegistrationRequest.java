package com.being.institutemanagementsystem.features.data.model.experience.student;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateRegistrationRequest {
    @NotBlank(message = "user.name.not.blank.message")
    private String name;
    @NotBlank(message = "user.courseName.not.blank.message")
    private String courseName;

    private  String instituteName;
    @NotBlank(message = "user.email.not.blank.message")
    private String email;
    @NotBlank(message = "user.contact.not.blank.message")
    private  String contact;
    @NotBlank(message = "user.location.not.blank.message")
    private  String location;

    public CreateRegistrationRequest(String veera, String java, String qspider, String s, String s1, String hyderabad) {
    }
}
