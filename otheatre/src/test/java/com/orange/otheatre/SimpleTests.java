package com.orange.otheatre;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.hateoas.alps.Doc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("alex")
public class SimpleTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHome() {
        String message = this.restTemplate.getForObject("/", String.class);
        assertNotEquals("alfa", message);
    }


    @Test
    public void testAdmin() {
        String message = this.restTemplate.getForObject("/admin",String.class);
        assertNotEquals("String",message);
    }

    @Test
    public void testManageEvents() {
        String message = this.restTemplate.getForObject("/manageEvents", String.class);
        assertEquals("String",message);
    }


}
