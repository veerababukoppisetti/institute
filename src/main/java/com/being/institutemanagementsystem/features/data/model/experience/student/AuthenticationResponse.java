package com.being.institutemanagementsystem.features.data.model.experience.student;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(of = {"accessToken"})
@EqualsAndHashCode(of = {"accessToken"})
@Data
@Builder
public class AuthenticationResponse {

    /**
     * The id of the user
     */
    private String id;
    /**
     * Access token.
     */
    private String accessToken;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * Email of the user.
     */
    private String email;

}
