package org.acme.camel.model;

import org.acme.camel.generated.PersonMessage.PersonProto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class PersonRequest {
    private final String firstName;
    private final String lastName;
    private final Integer age;

    public PersonRequest(final String firstName, final String lastName, final Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public static PersonProto toMessage(final PersonRequest personRequest) {
        return PersonProto.newBuilder()
                .setFirstName(personRequest.firstName)
                .setLastName(personRequest.lastName)
                .setAge(personRequest.age)
                .build();
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
