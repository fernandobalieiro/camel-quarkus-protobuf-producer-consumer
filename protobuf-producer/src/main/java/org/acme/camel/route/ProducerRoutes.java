package org.acme.camel.route;

import org.acme.camel.model.PersonRequest;
import org.acme.camel.processor.PersonProducer;
import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ProducerRoutes extends RouteBuilder {

    private final PersonProducer personProducer;

    @Inject
    public ProducerRoutes(final PersonProducer personProducer) {
        this.personProducer = personProducer;
    }

    @Override
    public void configure() {
        from("seda://produce")
                .log("${body}")
                .process(exchange -> {
                    final var personRequest = exchange.getIn().getBody(PersonRequest.class);
                    final var personMessage = personRequest.asMessage();
                    exchange.getIn().setBody(personMessage);
                })
                .to("seda://toQueue");

        from("timer:foo?period=30000")
                .process(personProducer)
                .log("${body}")
                .to("seda://toQueue");

        from("seda://toQueue")
                .log("Marshalling Message: ${body}")
                .marshal().protobuf("org.acme.camel.generated.PersonMessage$PersonProto")
                .to("rabbitmq://people?addresses={{rabbitmq.url}}" +
                        "&queue={{rabbitmq.queue}}" +
                        "&vhost={{rabbitmq.vhost}}" +
                        "&username={{rabbitmq.username}}" +
                        "&password={{rabbitmq.password}}" +
                        "&exchangeType=topic" +
                        "&autoDelete=false")
                .log("Message successfully sent to queue.");
    }
}
