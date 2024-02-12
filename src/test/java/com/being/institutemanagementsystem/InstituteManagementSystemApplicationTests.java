package com.being.institutemanagementsystem;

import com.being.institutemanagementsystem.features.data.model.experience.student.Registration;
import com.being.institutemanagementsystem.features.data.model.persistence.RegistrationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class InstituteManagementSystemApplicationTests {
	@Test
	void contextLoads() {
	}
}
