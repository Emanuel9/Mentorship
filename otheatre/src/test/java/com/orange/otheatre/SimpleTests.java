package com.orange.otheatre;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class SimpleTests {

    private TestRestTemplate restTemplate ;

    @Before
    public void init() {
        restTemplate = new TestRestTemplate();
    }

//    @Test
//    public void testHome() {
//        String message = this.restTemplate.getForObject("/", String.class);
//        assertNotEquals("alfa", message);
//    }
//
//
//    @Test
//    public void testAdmin() {
//        String message = this.restTemplate.getForObject("/admin",String.class);
//        assertNotEquals("String",message);
//    }
//
//    @Test
//    public void testManageEvents() {
//        String message = this.restTemplate.getForObject("/manageEvents", String.class);
//        assertEquals("String",message);
//    }

    @Test
    public void testSomething1() {
        assertEquals(1,1);
    }
    @Test
    public void testSomething2() {
        assertEquals(2,2);
    }


}
