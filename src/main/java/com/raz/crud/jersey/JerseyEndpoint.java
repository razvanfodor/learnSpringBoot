package com.raz.crud.jersey;

import com.raz.crud.DiscountRepository;
import com.raz.crud.entity.Discount;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("jersey-discount")
public class JerseyEndpoint {

    @Inject
    private DiscountRepository discountRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Discount> getDiscounts() {
        return discountRepo.findAll();
    }
}
