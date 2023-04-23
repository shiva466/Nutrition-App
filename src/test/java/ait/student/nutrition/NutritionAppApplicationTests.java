package ait.student.nutrition;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class NutritionAppApplicationTests {

	@Test
	void contextLoads() 
	{
		ApplicationContext context = SpringApplication.run(NutritionAppApplication.class);
	    assertThat(context).isNotNull();	
	}

}
