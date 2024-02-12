package com.being.institutemanagementsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.being.institutemanagementsystem.features.data.model.experience.student.CreateRegistrationRequest;
import com.being.institutemanagementsystem.features.data.model.experience.student.Registration;
import com.being.institutemanagementsystem.features.web.api.RegistrationApi;
import com.being.institutemanagementsystem.features.web.services.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

class RegistrationControllerTest {

    private final RegistrationService registrationService = mock(RegistrationService.class);
    private final RegistrationApi registrationController = new RegistrationApi(registrationService);

    @Test
    void createRegistration_ValidPayload_ReturnsCreatedResponse() {
        // Arrange
        CreateRegistrationRequest request = new CreateRegistrationRequest();
        request.setName("John Doe");
        request.setEmail("john.doe@example.com");
        request.setContact("1234567890");
        request.setCourseName("Computer Science");
        request.setInstituteName("University of Example");
        request.setLocation("Example City");
        Registration expectedRegistration = new Registration();
        expectedRegistration.setId(1);
        expectedRegistration.setName("John Doe");
        expectedRegistration.setEmail("john.doe@example.com");
        expectedRegistration.setContact("1234567890");
        expectedRegistration.setCourseName("Computer Science");
        expectedRegistration.setInstituteName("University of Example");
        expectedRegistration.setLocation("Example City");
        when(registrationService.createRegistration(request)).thenReturn(expectedRegistration);

        // Act
        ResponseEntity<Registration> response = registrationController.createRegistration(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedRegistration, response.getBody());
    }


}
