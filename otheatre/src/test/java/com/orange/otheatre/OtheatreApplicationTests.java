package com.orange.otheatre;

import static org.assertj.core.api.Assertions.*;

import com.orange.otheatre.otheatre.controller.HomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OtheatreApplicationTests {

	@Autowired
	HomeController homeController;

	@Test
	public void contextLoads() throws Exception {
	}

}
