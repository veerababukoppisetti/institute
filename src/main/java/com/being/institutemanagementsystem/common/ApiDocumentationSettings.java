package com.being.institutemanagementsystem.common;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "com.being.institutemanagementsystem.common")
public class ApiDocumentationSettings {
    /** Reference to the title. */
    private String title;

    /** Brief description about the API. */
    private String description;

    /** Security scheme for our APIs. */
    private ApiSecurityScheme securityScheme = new ApiSecurityScheme();

    /** Type of license for this software. */
    private String license;

    /** License url. */
    private String licenseUrl;

    /** Terms of service url. */
    private String termsOfServiceUrl;

    /** Version number. */
    private String version;

    /** Contact information. */
    private Contact contact = new Contact();

    /** Base package. */
    private String basePackage;

    /**
     * Contact details for the API.
     */
    @Data
    public static class Contact {
        /** Contact name. */
        private String name;

        /** Contact url. */
        private String url;

        /** Email address of the contact. */
        private String email;
    }

    /**
     * Security scheme for the exposed APIs.
     */
    @Data
    public static class ApiSecurityScheme {
        /** Default security scheme name. */
        public static final String DEFAULT_SECURITY_SCHEME_NAME = "bearerAuth";

        /** Security scheme name. */
        private String name = ApiSecurityScheme.DEFAULT_SECURITY_SCHEME_NAME;

        /** Security scheme. */
        private String scheme = "bearer";

        /** Security scheme type - HTTP, OAuth2, etc. */
        private String type = "HTTPS";

        /** Bearer format. */
        private String bearerFormat = "JWT";
    }
}