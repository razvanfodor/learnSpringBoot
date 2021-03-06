package com.raz.crud.unit;

import com.raz.crud.DiscountController;
import com.raz.crud.DiscountRepository;
import com.raz.crud.entity.Discount;
import com.raz.crud.jdbc.JDBCRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DiscountControllerTest {

    @InjectMocks
    private DiscountController underTest;

    @Mock
    private DiscountRepository discountRepository;

    @Mock
    private Logger logger;

    @Mock
    private JDBCRepository jdbcRepository;

    @Test
    public void testGetPaginated() {
        when(discountRepository.findAll()).thenReturn(Arrays.asList(
                new Discount() {{
                    setId(1L);
                }},
                new Discount(){{
                    setId(2L);
                }}));

        Iterable<Discount> discounts = underTest.getAllDiscounts(null, null, null);
        assertThat(discounts)
                .hasSize(2)
                .extracting(Discount::getId)
                .containsExactlyInAnyOrder(1L, 2L);
    }
}
