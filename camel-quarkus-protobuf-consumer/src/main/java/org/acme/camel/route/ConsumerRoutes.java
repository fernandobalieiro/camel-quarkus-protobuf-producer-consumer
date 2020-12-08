package org.acme.camel.route;

import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsumerRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("rabbitmq://people?addresses={{rabbitmq.url}}" +
                "&queue={{rabbitmq.queue}}" +
                "&vhost={{rabbitmq.vhost}}" +
                "&username={{rabbitmq.username}}" +
                "&password={{rabbitmq.password}}" +
                "&exchangeType=topic" +
                "&autoDelete=false")
                .unmarshal().protobuf("org.acme.camel.generated.PersonMessage$PersonProto")
                .log("${body}")
                .log("rabbitmq:people");
    }
}
