package org.acme.camel.processor;

import org.acme.camel.data.entity.Person;
import org.acme.camel.data.repository.PersonRepository;
import org.acme.camel.generated.PersonMessage;
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
        var personMessage = exchange.getIn().getBody(PersonMessage.PersonProto.class);

        var person = Person.fromMessage(personMessage);

        personRepository.persist(person);

        exchange.getIn().setBody(person);
    }
}
