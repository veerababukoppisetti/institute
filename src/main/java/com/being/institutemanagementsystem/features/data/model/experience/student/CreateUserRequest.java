package com.being.institutemanagementsystem.features.data.model.experience.student;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "user.name.not.blank.message")
    private String username;
    @NotBlank(message = "user.name.not.blank.message")
    private  String password;
    @NotBlank(message = "user.email.not.blank.message")
    private String email;
}
