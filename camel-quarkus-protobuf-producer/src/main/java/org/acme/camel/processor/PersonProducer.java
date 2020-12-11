package org.acme.camel.processor;

import com.github.javafaker.Faker;
import org.acme.camel.model.PersonRequest;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PersonProducer implements Processor {

    private final Faker faker;

    @Inject
    public PersonProducer(final Faker faker) {
        this.faker = faker;
    }

    @Override
    public void process(final Exchange exchange) {
        var person = new PersonRequest(faker.name().firstName(), faker.name().lastName(), faker.number().numberBetween(1, 105));
        exchange.getIn().setBody(PersonRequest.toMessage(person));
    }
}
