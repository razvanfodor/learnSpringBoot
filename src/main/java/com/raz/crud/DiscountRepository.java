package com.raz.crud;

import com.raz.crud.entity.Discount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DiscountRepository extends PagingAndSortingRepository<Discount, Long> {
}
