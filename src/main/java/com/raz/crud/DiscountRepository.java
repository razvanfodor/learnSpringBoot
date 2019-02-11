package com.raz.crud;

import com.raz.crud.entity.Discount;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscountRepository extends PagingAndSortingRepository<Discount, Long> {

    List<Discount> selectByName(@Param(Discount.PARAM_NAME) String name);

}
