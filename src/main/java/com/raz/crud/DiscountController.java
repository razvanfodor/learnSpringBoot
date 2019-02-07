package com.raz.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Optional;

@RestController
public class DiscountController {

    @Autowired
    private DiscountRepository discountRepository;

    @Inject
    private AsynchronousJobBean asyncJobInj;

    @Value("${apachePort}")
    private String apachePort;

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
        System.out.println("Apache port: " + apachePort);
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
}
