package com.heliumhealth.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter
@Entity
public class IPAddress {

    @Id
    private String address;
}
