package org.acme.camel.route;

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
    public void configure() throws Exception {
        from("timer:foo?period=1000")
                .process(personProducer)
                .log("${body}")
                .marshal().protobuf("org.acme.camel.generated.PersonMessage$PersonProto")
                .to("rabbitmq://people?addresses={{rabbitmq.url}}" +
                        "&queue={{rabbitmq.queue}}" +
                        "&vhost={{rabbitmq.vhost}}" +
                        "&username={{rabbitmq.username}}" +
                        "&password={{rabbitmq.password}}" +
                        "&exchangeType=topic" +
                        "&autoDelete=false")
                .log("rabbitmq:people");
    }
}
