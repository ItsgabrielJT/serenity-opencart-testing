package com.company.automation.utils;

import com.company.automation.models.CustomerData;
import com.github.javafaker.Faker;

import java.util.Locale;

/**
 * FakerDataGenerator
 *
 * Genera datos de prueba realistas usando la librería JavaFaker.
 * Centraliza la creación de datos falsos para evitar duplicación.
 *
 * Principio DRY: un único lugar para generar datos de prueba con Faker.
 */
public class FakerDataGenerator {

    private static final Faker faker = new Faker(new Locale("en-US"));

    private FakerDataGenerator() {}

    /**
     * Genera un CustomerData completo con datos aleatorios válidos.
     */
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

    /**
     * Genera un email único de prueba con prefijo dado.
     */
    public static String randomEmail(String prefix) {
        return prefix + "." + faker.random().nextInt(10000) + "@testmail.com";
    }

    /**
     * Genera un número de teléfono aleatorio.
     */
    public static String randomPhone() {
        return faker.phoneNumber().phoneNumber();
    }

    /**
     * Genera un nombre de producto aleatorio.
     */
    public static String randomProductName() {
        return faker.commerce().productName();
    }

    public static Faker getFaker() {
        return faker;
    }
}
