package com.company.automation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ProductData
 *
 * Modelo de datos para productos cargados desde products.csv.
 * Usado en Data Driven Testing con TestDataLoader.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductData {

    @JsonProperty("productId")
    private String productId;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("price")
    private String price;

    @JsonProperty("quantity")
    private String quantity;

    public ProductData() {}

    public String getProductId()   { return productId; }
    public String getProductName() { return productName; }
    public String getPrice()       { return price; }
    public String getQuantity()    { return quantity; }

    public void setProductId(String productId)     { this.productId   = productId; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setPrice(String price)             { this.price       = price; }
    public void setQuantity(String quantity)       { this.quantity    = quantity; }

    @Override
    public String toString() {
        return productName + " (id=" + productId + ", price=" + price + ")";
    }
}
