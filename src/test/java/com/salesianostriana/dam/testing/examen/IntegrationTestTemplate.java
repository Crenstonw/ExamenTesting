package com.salesianostriana.dam.testing.examen;

import com.salesianostriana.dam.testing.examen.security.jwt.JwtProvider;
import com.salesianostriana.dam.testing.examen.security.user.model.User;
import com.salesianostriana.dam.testing.examen.security.user.model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
class IntegrationTestTemplate {

	@LocalServerPort
	private int port;

	JwtProvider jwtProvider;
	@Autowired
	private TestRestTemplate restTemplate;

	private HttpHeaders adminHeaders;
	private PasswordEncoder passwordEncoder;


	@BeforeEach
	void setUp() {
		User admin = new User();
		admin.setId(UUID.randomUUID());
		admin.setFullName("Alejandro Rubens");
		admin.setPassword(passwordEncoder.encode("1234"));
		admin.setRoles(Set.of(UserRole.ADMIN));
		restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		adminHeaders.setContentType(MediaType.APPLICATION_JSON);
		adminHeaders.setBearerAuth(jwtProvider.generateToken(admin));

	}

	@Test
	@WithMockUser("ADMIN")
	void test() {

		assertTrue(true);
	}

}
