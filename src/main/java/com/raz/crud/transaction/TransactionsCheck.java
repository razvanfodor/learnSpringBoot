package com.raz.crud.transaction;

import com.raz.crud.entity.Discount;
import com.raz.crud.DiscountRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;

@Service
public class TransactionsCheck {

    @Inject
    private DiscountRepository discountRepository;

    public void saveNoTransaction(Discount discount){
        discountRepository.save(discount);
    }

    public void saveAndThrowExceptionNoTransaction(Discount discount){
        discountRepository.save(discount);
        throw new RuntimeException();
    }

    @Transactional
    public void saveWithTransaction(Discount discount){
        discountRepository.save(discount);
    }

    @Transactional
    public void saveAndThrowExceptionWithTransaction(Discount discount){
        discountRepository.save(discount);
        throw new RuntimeException();
    }
}
