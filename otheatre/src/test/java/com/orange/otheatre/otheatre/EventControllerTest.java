package com.orange.otheatre.otheatre;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.otheatre.otheatre.entities.Event;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestHelper testHelper;

    private Event event;

    private ObjectMapper mapper;

    @Before
    public void initTest() {
        testHelper.cleanDataBase();
        event = testHelper.createEvent();
        mapper = new ObjectMapper();
    }

    @Test
    public void testBasicInMemoryAuthentication() throws Exception {


    }
}
