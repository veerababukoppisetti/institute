package com.being.institutemanagementsystem.filter.security;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;


@EqualsAndHashCode(
        of = {"id"},
        callSuper = false)
@ToString(of = {"id"})
@Getter
@Setter
public class UserPrincipal {
    /** Unique identifier of the user. */

    private String id;

    /** User name of the user. */
    private String username;

    /** Represents email of user */
    private String email;

    /** Password of the user. */
    private String password;


    public UserPrincipal(final UserDetails user, String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public UserDetails user;
}
