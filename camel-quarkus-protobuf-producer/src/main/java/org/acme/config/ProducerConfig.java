package org.acme.config;

import com.github.javafaker.Faker;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class ProducerConfig {

    @Produces
    public Faker faker() {
        return new Faker();
    }
}
