package com.company.automation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CustomerData
 *
 * Modelo de datos del cliente para el flujo de checkout.
 * Soporta construcción fluida (builder pattern) para Data Driven Testing.
 *
 * Principio SOLID: Single Responsibility – solo representa datos del cliente.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerData {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("postcode")
    private String postcode;

    @JsonProperty("country")
    private String country;

    @JsonProperty("region")
    private String region;

    // ─── Constructor vacío para deserialización Jackson ───────────────────────
    public CustomerData() {}

    // ─── Builder ─────────────────────────────────────────────────────────────
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final CustomerData instance = new CustomerData();

        public Builder firstName(String v)  { instance.firstName  = v; return this; }
        public Builder lastName(String v)   { instance.lastName   = v; return this; }
        public Builder email(String v)      { instance.email      = v; return this; }
        public Builder telephone(String v)  { instance.telephone  = v; return this; }
        public Builder address(String v)    { instance.address    = v; return this; }
        public Builder city(String v)       { instance.city       = v; return this; }
        public Builder postcode(String v)   { instance.postcode   = v; return this; }
        public Builder country(String v)    { instance.country    = v; return this; }
        public Builder region(String v)     { instance.region     = v; return this; }

        public CustomerData build() { return instance; }
    }

    // ─── Getters ─────────────────────────────────────────────────────────────
    public String getFirstName()  { return firstName; }
    public String getLastName()   { return lastName; }
    public String getEmail()      { return email; }
    public String getTelephone()  { return telephone; }
    public String getAddress()    { return address; }
    public String getCity()       { return city; }
    public String getPostcode()   { return postcode; }
    public String getCountry()    { return country; }
    public String getRegion()     { return region; }

    @Override
    public String toString() {
        return firstName + " " + lastName + " <" + email + ">";
    }
}
