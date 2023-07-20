package com.acme.learning.platform.profiles.domain.model.aggregates;


import com.acme.learning.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.acme.learning.platform.profiles.domain.model.valueobjects.PersonName;
import com.acme.learning.platform.profiles.domain.model.valueobjects.StreetAddress;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;


@Entity
public class Profile extends AbstractAggregateRoot<Profile> {
    @Id
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
}
