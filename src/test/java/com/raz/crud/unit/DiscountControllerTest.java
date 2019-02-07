package com.raz.crud.unit;

import com.raz.crud.entity.Discount;
import com.raz.crud.DiscountController;
import com.raz.crud.DiscountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DiscountControllerTest {

    @InjectMocks
    private DiscountController underTest;

    @Mock
    private DiscountRepository discountRepository;

    @Test
    public void testGetPaginated() {
        when(discountRepository.findAll()).thenReturn(Arrays.asList(
                new Discount() {{
                    setId(1L);
                }},
                new Discount(){{
                    setId(2L);
                }}));

        Iterable<Discount> discounts = underTest.getAllDiscounts(null, null);
        assertThat(discounts)
                .hasSize(2)
                .extracting(Discount::getId)
                .containsExactlyInAnyOrder(1L, 2L);
    }
}
