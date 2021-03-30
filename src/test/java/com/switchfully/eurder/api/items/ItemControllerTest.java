package com.switchfully.eurder.api.items;

import com.switchfully.eurder.domain.items.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ItemControllerTest {
    @Value("${server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAddItem() {
        CreateItemDTO createItemDTO = new CreateItemDTO("item", "description",
                new Price(20.5, Currency.getInstance("EUR")), 2);

        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/items", createItemDTO, String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }
}
