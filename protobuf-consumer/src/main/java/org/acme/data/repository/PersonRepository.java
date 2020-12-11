package org.acme.data.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.acme.data.entity.Person;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonRepository implements PanacheMongoRepository<Person> {
}
