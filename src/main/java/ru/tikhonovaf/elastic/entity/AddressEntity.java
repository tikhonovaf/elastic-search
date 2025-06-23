package ru.tikhonovaf.elastic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses")
public class AddressEntity {
    @Id
    private Long id;
    private String region;
    private String city;
    private String street;
    private String houseNumber;
    // геттеры и сеттеры
}