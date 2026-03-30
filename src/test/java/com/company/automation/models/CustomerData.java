package com.company.automation.models;

public class CustomerData {

    private String firstName;

    private String lastName;

    private String email;

    private String telephone;

    private String address;

    private String city;

    private String postcode;

    private String country;

    private String region;

    public CustomerData() {}

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
