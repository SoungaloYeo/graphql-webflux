package com.adervio.controller;

import com.adervio.domain.Customer;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
public class CustomerController {

    private static final List<Customer> data = List.of(
            Customer.builder().id(UUID.randomUUID().toString()).firstname("YEO").lastname("Soungalo").build(),
            Customer.builder().id(UUID.randomUUID().toString()).firstname("Carl").lastname("Camille").build());


    // @SchemaMapping(typeName = "Query", field = "customers")
    @QueryMapping
    public Flux<Customer> customers() {
        return Flux.just(data.get(0), data.get(1)
        );
    }


    // @SchemaMapping(typeName = "Query", field = "customersByFirstname")
    @QueryMapping
    public Flux<Customer> customersByFirstname(@Argument String firstname) {
        return Flux.just(Objects.requireNonNull(this.data
                .stream()
                .filter(customer -> customer.getFirstname().equalsIgnoreCase(firstname))
                .findFirst().orElse(null)));
    }

}
