package com.company.automation.utils;

import com.company.automation.models.CustomerData;
import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerDataGenerator {

    private static final Faker faker = new Faker(new Locale("en-US"));

    private FakerDataGenerator() {}

    
    public static CustomerData randomCustomer() {
        return CustomerData.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .telephone(faker.phoneNumber().phoneNumber())
                .address(faker.address().streetAddress())
                .city(faker.address().city())
                .postcode(faker.address().zipCode())
                .country("United States")
                .region("New York")
                .build();
    }

    
    public static String randomEmail(String prefix) {
        return prefix + "." + faker.random().nextInt(10000) + "@testmail.com";
    }

    
    public static String randomPhone() {
        return faker.phoneNumber().phoneNumber();
    }

    
    public static String randomProductName() {
        return faker.commerce().productName();
    }

    public static Faker getFaker() {
        return faker;
    }
}
