package com.being.institutemanagementsystem.features.web.api;

import com.being.institutemanagementsystem.common.ApiDocumentationSettings;
import com.being.institutemanagementsystem.features.data.model.experience.student.CreateRegistrationRequest;
import com.being.institutemanagementsystem.features.data.model.experience.student.Registration;
import com.being.institutemanagementsystem.features.data.model.experience.student.UpdateRegistrationRequest;
import com.being.institutemanagementsystem.features.web.services.RegistrationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(RegistrationApi.rootEndPoint)
@SecurityRequirement(name = "bearerAuth")
public class RegistrationApi {
    /**
     * Root end point.
     */
    public static final String rootEndPoint = "/institute-management-service";
    public static final String API_TAG ="";
  private  final RegistrationService registrationService;

    public RegistrationApi(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    @Operation(
            method = "createRegistration",
            summary = "Create a new Registration.",
            description = "This API is used to create a new Registration in the system.",
            tags = {RegistrationApi.API_TAG},
            security = {
                    @SecurityRequirement(
                            name = "bearerAuth")
//                                    ApiDocumentationSettings.ApiSecurityScheme
//                                            .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created a new Registration in the system.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "403",
                            description = "You do not have permissions to perform this operation.",
                            content = @Content)
            })
    @PostMapping(value = "/registration")
   public ResponseEntity<Registration>createRegistration(@Valid @RequestBody  final CreateRegistrationRequest payload){
        // Delegate to the service layer.
        final Registration newInstance = registrationService.createRegistration(payload);
        // Build a response entity object and return it.
        return ResponseEntity.status(HttpStatus.CREATED).body(newInstance);
   }


    @Operation(
            method = "updateRegistration",
            summary = "Update an existing updateRegistration.",
            description = "This API is used to update an existing updateRegistration in the system.",
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
                            responseCode = "200",
                            description = "Successfully updated an existing updateRegistration in the system.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "403",
                            description = "You do not have permissions to perform this operation.",
                            content = @Content)
            })
   @PutMapping(value = "/institutes/{instituteId}")
    public ResponseEntity<Registration>updateRegistration(@PathVariable(name = "instituteId") final Integer instituteId,
                                                         @Valid @RequestBody final UpdateRegistrationRequest payload){
       // Delegate to the service layer.
       final Registration matchingInstance = registrationService.updateRegistration(instituteId,payload);
       return ResponseEntity.ok(matchingInstance);
   }

    @Operation(
            method = "findByInstitute",
            summary = "Find an existing Institute.",
            description = "This API is used to find an existing Institute in the system.",
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
                            responseCode = "200",
                            description =
                                    "Successfully retrieved the details of an existing Institute in the system.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "403",
                            description = "You do not have permissions to perform this operation.",
                            content = @Content)
            })
    @GetMapping(value = "/institutes/{instituteId}")

    public ResponseEntity<Registration>findByInstitute(@PathVariable (name = "instituteId")final Integer instituteId){
        // Delegate to the service layer.
        final  Registration matchingRegistration = registrationService.findByInstitute(instituteId);
        return ResponseEntity.ok(matchingRegistration);
    }


}


