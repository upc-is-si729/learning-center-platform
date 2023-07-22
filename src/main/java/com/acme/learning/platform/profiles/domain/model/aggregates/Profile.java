package com.acme.learning.platform.profiles.domain.model.aggregates;


import com.acme.learning.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.acme.learning.platform.profiles.domain.model.valueobjects.PersonName;
import com.acme.learning.platform.profiles.domain.model.valueobjects.StreetAddress;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;


@Entity
public class Profile extends AbstractAggregateRoot<Profile> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private PersonName name;
    @Embedded
    private EmailAddress email;

    @Embedded
    private StreetAddress address;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Profile(String firstName, String lastName, String email, String streetAddress, String city, String state, String zipCode) {
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.address = new StreetAddress(streetAddress, city, state, zipCode);
    }

    public Profile() {
    }

    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }

    public void updateAddress(String streetAddress, String city, String state, String zipCode) {
        this.address = new StreetAddress(streetAddress, city, state, zipCode);
    }

    public String getFullName() {
        return this.name.getFullName();
    }

    public String getStreetAddress() {
        return this.address.getStreetAddress();
    }

    public Long getId() {
        return this.id;
    }
}
