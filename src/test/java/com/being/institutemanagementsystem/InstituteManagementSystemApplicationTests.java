package com.being.institutemanagementsystem;

import com.being.institutemanagementsystem.features.data.model.experience.student.CreateRegistrationRequest;
import com.being.institutemanagementsystem.features.data.repository.RegistrationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InstituteManagementSystemApplicationTests {
//	@Test
//	void contextLoads() {
//	}

 private  final RegistrationRepository registrationRepository;
	private static RestTemplate restTemplate;

	InstituteManagementSystemApplicationTests(RegistrationRepository registrationRepository) {
		this.registrationRepository = registrationRepository;
	}


	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}
	 String  url ="https://localhost:7777/institute-management-service/registration";

	@ParameterizedTest
	@MethodSource("testAddProduct")
	public  void testAddProduct() {


		CreateRegistrationRequest request = new CreateRegistrationRequest("veera", "java","Qspider","1234567","veera@gmail.com","hyderabad");
		CreateRegistrationRequest response = restTemplate.postForObject(url, request, CreateRegistrationRequest.class);
		assertEquals("veera", response.getName());
		assertEquals(1, registrationRepository.findAll().size());
	}
}
