package org.acme.data.entity;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.acme.camel.generated.PersonMessage.PersonProto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.codecs.pojo.annotations.BsonProperty;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

@MongoEntity(collection = "people")
public class Person extends PanacheMongoEntity {

    @BsonProperty("first_name")
    private final String firstName;

    @BsonProperty("last_name")
    private final String lastName;

    private final Integer age;

    public Person(final String firstName, final String lastName, final Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public static Person fromMessage(final PersonProto message) {
        return new Person(message.getFirstName(), message.getLastName(), message.getAge());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
