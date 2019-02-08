package com.raz.crud;

import com.raz.crud.async.AsynchronousJobBean;
import com.raz.crud.entity.Discount;
import com.raz.crud.transaction.TransactionsCheck;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

@RestController()
public class DiscountController {

    @Autowired
    private DiscountRepository discountRepository;

    @Inject
    private AsynchronousJobBean asyncJobInj;

    @Value("${application.apachePort}")
    private String apachePort;

    @Inject
    private TransactionsCheck transactionCheck;

    @Inject
    private Logger logger;

    @RequestMapping("/")
    public String home(){
      return "Welcome Home man!";
    }

    @RequestMapping("/test")
    public String test(){
        return "This is a test";
    }

    @RequestMapping("/discount")
    public Iterable<Discount> getAllDiscounts(@RequestParam(required = false) Integer pageNumber,
                                              @RequestParam(required = false) Integer pageSize){
        logger.info("Apache port: " + apachePort);
        if (pageNumber == null || pageSize == null){
            return discountRepository.findAll();
        }
        return discountRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @RequestMapping("/discount/{id}")
    public Optional<Discount> getDiscount(@PathVariable("id") Long id){
        Optional<Discount> discount = discountRepository.findById(id);
        asyncJobInj.start(id);
        return discount;
    }

    @PutMapping("/discount")
    @Transactional
    public void addDiscount(@RequestBody Discount discount){
        transactionCheck.saveNoTransaction(discount);
        discount.setId(null);
        transactionCheck.saveAndThrowExceptionNoTransaction(discount);
    }
}
