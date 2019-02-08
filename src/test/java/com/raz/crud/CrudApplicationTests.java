package com.raz.crud;

import com.jayway.restassured.RestAssured;
import com.raz.crud.entity.Discount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static com.jayway.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrudApplicationTests {

	@Autowired
	private DiscountController discountController;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testGetDiscount() {
		Optional<Discount> discount = discountController.getDiscount(1L);
		assertThat(discount).isPresent().get().extracting(Discount::getId).isEqualTo(1L);
	}

	@Test
	public void getHttpCall() {
		RestAssured.port = 8080;
		RestAssured.baseURI = "http://localhost"; // replace as appropriate
		get("/discount/1").then().assertThat().body("id", equalTo(1));
	}
}