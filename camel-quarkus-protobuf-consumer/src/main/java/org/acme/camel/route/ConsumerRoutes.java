package org.acme.camel.route;

import org.acme.camel.processor.PersonDatabaseProcessor;
import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ConsumerRoutes extends RouteBuilder {

    private final PersonDatabaseProcessor personDatabaseProcessor;

    @Inject
    public ConsumerRoutes(final PersonDatabaseProcessor personDatabaseProcessor) {
        this.personDatabaseProcessor = personDatabaseProcessor;
    }

    @Override
    public void configure() {
        from("rabbitmq://people?addresses={{rabbitmq.url}}" +
                "&queue={{rabbitmq.queue}}" +
                "&vhost={{rabbitmq.vhost}}" +
                "&username={{rabbitmq.username}}" +
                "&password={{rabbitmq.password}}" +
                "&exchangeType=topic" +
                "&autoDelete=false")
                .unmarshal().protobuf("org.acme.camel.generated.PersonMessage$PersonProto")
                .log("Received Message: ${body}")
                .process(personDatabaseProcessor)
                .log("Finished. Person successfully created in database: ${body}");
    }
}
