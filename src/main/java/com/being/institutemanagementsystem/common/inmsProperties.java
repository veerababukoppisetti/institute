package com.being.institutemanagementsystem.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class inmsProperties {
    private Authentication auth = new Authentication();
    private Notification notification = new Notification();

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Notification {
//        private NotificationMode mode = NotificationMode.EMAIL;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class Authentication {
        /** Token related settings. */
        private AuthToken token = new AuthToken();

        /** Validate Token url is used for fetching the url of the API to validate the token. */
        private ValidateTokenUrl validateTokenUrl = new ValidateTokenUrl();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class AuthToken {
        /** Secret that is used for signing the token. */
        private String secret;

        /** JWT Token expiration interval in milliseconds. Defaulted to 7 days. */
        private Integer expirationIntervalInHours = 7 * 24;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ValidateTokenUrl {
        /** Url of the validate token API. */
        private String url;
    }
}