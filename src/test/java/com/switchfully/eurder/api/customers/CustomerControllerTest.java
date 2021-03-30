package com.switchfully.eurder.api.customers;

import com.switchfully.eurder.domain.users.customers.Address;
import com.switchfully.eurder.domain.users.customers.PhoneNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CustomerControllerTest {

    @Value("${server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

/*    @Test
    public void testCreateCustomer() {
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO("Rick", "Sanchez", "rick@sanchez.com",
                new Address("universe", "100A", "Universe", "3000"),
                new PhoneNumber(500, "123456789"));

        RestAssured.given()
                .body(createCustomerDTO)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

    }*/

    @Test
    public void testAddCustomer() {
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO("Rick", "Sanchez", "rick@sanchez.com",
                new Address("universe", "100A", "Universe", "3000"),
                new PhoneNumber(500, "123456789"));
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/customers", createCustomerDTO, String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

}
