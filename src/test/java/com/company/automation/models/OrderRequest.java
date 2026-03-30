package com.company.automation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

    @JsonProperty("customer_id")
    private Integer customerId;

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("products")
    private List<OrderProduct> products;

    @JsonProperty("payment_address")
    private AddressData paymentAddress;

    @JsonProperty("shipping_address")
    private AddressData shippingAddress;

    public OrderRequest() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final OrderRequest instance = new OrderRequest();

        public Builder customerId(Integer v)            { instance.customerId      = v; return this; }
        public Builder firstName(String v)              { instance.firstName       = v; return this; }
        public Builder lastName(String v)               { instance.lastName        = v; return this; }
        public Builder email(String v)                  { instance.email           = v; return this; }
        public Builder telephone(String v)              { instance.telephone       = v; return this; }
        public Builder products(List<OrderProduct> v)   { instance.products        = v; return this; }
        public Builder paymentAddress(AddressData v)    { instance.paymentAddress  = v; return this; }
        public Builder shippingAddress(AddressData v)   { instance.shippingAddress = v; return this; }

        public OrderRequest build() { return instance; }
    }

    public Integer getCustomerId()          { return customerId; }
    public String getFirstName()            { return firstName; }
    public String getLastName()             { return lastName; }
    public String getEmail()               { return email; }
    public String getTelephone()            { return telephone; }
    public List<OrderProduct> getProducts() { return products; }
    public AddressData getPaymentAddress()  { return paymentAddress; }
    public AddressData getShippingAddress() { return shippingAddress; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OrderProduct {
        @JsonProperty("product_id")  private Integer productId;
        @JsonProperty("quantity")    private Integer quantity;

        public OrderProduct() {}
        public OrderProduct(Integer productId, Integer quantity) {
            this.productId = productId;
            this.quantity  = quantity;
        }
        public Integer getProductId() { return productId; }
        public Integer getQuantity()  { return quantity; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AddressData {
        @JsonProperty("firstname")   private String firstName;
        @JsonProperty("lastname")    private String lastName;
        @JsonProperty("address_1")   private String address1;
        @JsonProperty("city")        private String city;
        @JsonProperty("postcode")    private String postcode;
        @JsonProperty("country_id")  private Integer countryId;
        @JsonProperty("zone_id")     private Integer zoneId;

        public AddressData() {}
        public String getFirstName()    { return firstName; }
        public String getLastName()     { return lastName; }
        public String getAddress1()     { return address1; }
        public String getCity()         { return city; }
        public String getPostcode()     { return postcode; }
        public Integer getCountryId()   { return countryId; }
        public Integer getZoneId()      { return zoneId; }

        public static AddressData of(String firstName, String lastName,
                                     String address1, String city, String postcode,
                                     int countryId, int zoneId) {
            AddressData a = new AddressData();
            a.firstName = firstName;
            a.lastName  = lastName;
            a.address1  = address1;
            a.city      = city;
            a.postcode  = postcode;
            a.countryId = countryId;
            a.zoneId    = zoneId;
            return a;
        }
    }
}
