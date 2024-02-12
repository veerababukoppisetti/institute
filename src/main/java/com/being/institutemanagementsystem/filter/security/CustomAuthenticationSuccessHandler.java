package com.being.institutemanagementsystem.filter.security;

import com.being.institutemanagementsystem.common.inmsProperties;
import com.being.institutemanagementsystem.features.data.model.experience.student.AuthenticationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Objects;

/**
 * <<Provide a brief description about the class>>
 *
 * @author veera babu
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final inmsProperties inmsProperties;

    public CustomAuthenticationSuccessHandler(com.being.institutemanagementsystem.common.inmsProperties inmsProperties) {
        this.inmsProperties = inmsProperties;
    }


    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
       final String accessToken = generateToken(authentication);

        User userPrincipal = User.class.cast(authentication.getPrincipal());
        // @formatter:off
        final AuthenticationResponse authResponse = AuthenticationResponse.builder()
                .username(userPrincipal.getUsername())
                .accessToken(accessToken)
                .build();
        // @formatter:on

        final PrintWriter writer = response.getWriter();
        CustomAuthenticationSuccessHandler.OBJECT_MAPPER.writeValue(writer, authResponse);
        writer.flush();
    };



    public String generateToken(final Authentication authentication) {
       final inmsProperties.AuthToken token = inmsProperties.getAuth().getToken();
        final Date currentDate = new Date();
        final Date tokenExpirationDate = DateUtils.addMilliseconds(currentDate, token.getExpirationIntervalInHours() * 60 * 60 * 1000);

        User userPrincipal = User.class.cast(authentication.getPrincipal());
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(currentDate)
                .setExpiration(tokenExpirationDate)
                .signWith(SignatureAlgorithm.HS512, "something")
                .compact();
    }

    public static <T> T getPrincipal(final Authentication authentication, final Class<T> userPrincipalType) {
        // Get the principal object from the authentication object.
        final Object authenticationPrincipal = Objects.nonNull(authentication) && Objects.nonNull(authentication.getPrincipal())
                ? authentication.getPrincipal()
                : null;

        // Cast the principal object to the specified type if possible, else return null.
        T principal = null;
        if (Objects.nonNull(authenticationPrincipal)) {
            principal = Adapter.adaptTo(authenticationPrincipal, userPrincipalType);
        }
        return principal;
    }

}
