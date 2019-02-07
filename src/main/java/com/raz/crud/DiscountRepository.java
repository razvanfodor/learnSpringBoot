package com.raz.crud;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DiscountRepository extends PagingAndSortingRepository<Discount, Long> {
}
