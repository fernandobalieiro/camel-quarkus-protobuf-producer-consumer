package org.acme.resource;

import org.acme.camel.model.PersonRequest;
import org.apache.camel.ProducerTemplate;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("persons")
@Produces(APPLICATION_JSON)
public class PersonResource {

    private final ProducerTemplate producerTemplate;

    @Inject
    public PersonResource(final ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response produce(final PersonRequest personRequest) {
        producerTemplate.asyncSendBody("seda://produce", personRequest);
        return Response.accepted().build();
    }
}
