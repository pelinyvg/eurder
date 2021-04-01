package com.switchfully.eurder.api.items;

import com.switchfully.eurder.domain.items.Price;
import com.switchfully.eurder.infrastructure.exceptions.InvalidEmailException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTest {
/*    @Value("${server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAddItemAuthorization() throws InvalidEmailException {
        CreateItemDTO createItemDTO = new CreateItemDTO("item", "description",
                new Price(20.5, Currency.getInstance("EUR")), 2);

        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/items", createItemDTO, String.class);
        assertEquals(403, responseEntity.getStatusCodeValue());
    }*/
}
