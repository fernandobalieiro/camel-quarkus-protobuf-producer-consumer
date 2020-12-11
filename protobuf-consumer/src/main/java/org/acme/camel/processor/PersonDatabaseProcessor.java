package org.acme.camel.processor;

import org.acme.camel.generated.PersonMessage.PersonProto;
import org.acme.data.entity.Person;
import org.acme.data.repository.PersonRepository;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PersonDatabaseProcessor implements Processor {

    private final PersonRepository personRepository;

    @Inject
    public PersonDatabaseProcessor(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void process(final Exchange exchange) {
        final var personMessage = exchange.getIn().getBody(PersonProto.class);

        final var person = Person.fromMessage(personMessage);

        personRepository.persist(person);

        exchange.getIn().setBody(person);
    }
}
