package com.danilomassoni.APISimple.integrationtests.swagger;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.danilomassoni.APISimple.config.TestConfigs;
import com.danilomassoni.APISimple.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@DisplayName("Test for Should Display Swagger UI Page")
	@Test
	void testShouldDisplaySwaggerUiPage() {

		var content = given()
				.basePath("/swagger-ui/index.html")
				.port(TestConfigs.SERVER_PORT)
				.when()
					.get()
				.then()
					.statusCode(200)
				.extract()
					.body()
						.asString();

		assertTrue(content.contains("Swagger UI"));

	}

}
