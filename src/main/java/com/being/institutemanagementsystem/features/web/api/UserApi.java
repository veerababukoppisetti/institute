package com.being.institutemanagementsystem.features.web.api;

import com.being.institutemanagementsystem.common.ApiDocumentationSettings;
import com.being.institutemanagementsystem.features.data.model.experience.student.CreateUserRequest;
import com.being.institutemanagementsystem.features.data.model.experience.student.User;
import com.being.institutemanagementsystem.features.web.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(UserApi.rootEndPoint)
public class UserApi {
private  final UserService userService;
    public static final String rootEndPoint = "/institute-management-service";

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            method = "createUser",
            summary = "Create a new User.",
            description = "This API is used to create a new User in the system.",
            tags = {RegistrationApi.API_TAG},
            security = {
                    @SecurityRequirement(
                            name =
                                    ApiDocumentationSettings.ApiSecurityScheme
                                            .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created a new User in the system.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "403",
                            description = "You do not have permissions to perform this operation.",
                            content = @Content)
            })
    @PostMapping(value = "/signup")
    public ResponseEntity<User>createUser(@Valid @RequestBody CreateUserRequest payload){

        // Delegate to the service layer.
        final User newInstance = userService.createUser(payload);
        // Build a response entity object and return it.
        return ResponseEntity.status(HttpStatus.CREATED).body(newInstance);
    }

}
