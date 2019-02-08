package com.raz.crud;

import com.raz.crud.entity.Discount;
import com.raz.crud.transaction.TransactionsCheck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionTests {

	public static final String DISCOUNT_1 = "new Discount 1";
	public static final String DISCOUNT_2 = "new Discount 2";

	@Autowired
	private TransactionsCheck transactionsCheck;

	@Autowired
	private DiscountRepository discountRepository;

	@Test
	public void testSaveNoTransaction() { //creates a jpa transaction inside the repository save method
		Discount discount1 = new Discount();
		discount1.setName(DISCOUNT_1);
		discount1.setCreationDate(new Date());

		Discount discount2 = new Discount();
		discount2.setName(DISCOUNT_2);
		discount1.setCreationDate(new Date());

		transactionsCheck.saveNoTransaction(discount1);
		Optional<Discount> foundDisc = discountRepository.findById(discount1.getId());

		assertThat(foundDisc).isPresent().get().extracting(Discount::getName).isEqualTo(DISCOUNT_1);
	}

	@Test
	public void testSaveNoTransactionThenFailNoTransaction() {
		Discount discount1 = new Discount();
		discount1.setName(DISCOUNT_1);
		discount1.setCreationDate(new Date());

		Discount discount2 = new Discount();
		discount2.setName(DISCOUNT_2);
		discount2.setCreationDate(new Date());

		transactionsCheck.saveNoTransaction(discount1);
		Optional<Discount> foundDisc = discountRepository.findById(discount1.getId());

		//discount 1 is persisted
		assertThat(foundDisc).isPresent().get().extracting(Discount::getName).isEqualTo(DISCOUNT_1);

		try {
			transactionsCheck.saveAndThrowExceptionNoTransaction(discount2);
			fail("should not get here");
		} catch (Exception e) {
		}
		//discount 2 is persisted because the transaction is inside the repository save method
		// and it get committed before the exception is thrown
		assertThat(discount2.getId()).isNotNull();
		foundDisc = discountRepository.findById(discount2.getId());
		assertThat(foundDisc).isPresent().get().extracting(Discount::getName).isEqualTo(DISCOUNT_2);

		//discount 1 is persisted
		foundDisc = discountRepository.findById(discount1.getId());
		assertThat(foundDisc).isPresent().get().extracting(Discount::getName).isEqualTo(DISCOUNT_1);
	}

	@Test
	public void testSaveNoTransactionThenFailWithTransaction() {
		Discount discount1 = new Discount();
		discount1.setName(DISCOUNT_1);
		discount1.setCreationDate(new Date());

		Discount discount2 = new Discount();
		discount2.setName(DISCOUNT_2);
		discount2.setCreationDate(new Date());

		transactionsCheck.saveNoTransaction(discount1);
		Optional<Discount> foundDisc = discountRepository.findById(discount1.getId());

		//discount 1 is persisted
		assertThat(foundDisc).isPresent().get().extracting(Discount::getName).isEqualTo(DISCOUNT_1);

		try {
			transactionsCheck.saveAndThrowExceptionWithTransaction(discount2);
			fail("should not get here");
		} catch (Exception e) {
		}
		//discount 2 is not persisted because the transaction is rolled back
		foundDisc = discountRepository.findById(discount2.getId());
		assertThat(foundDisc).isNotPresent();


		//discount 1 is persisted
		foundDisc = discountRepository.findById(discount1.getId());
		assertThat(foundDisc).isPresent().get().extracting(Discount::getName).isEqualTo(DISCOUNT_1);
	}
}