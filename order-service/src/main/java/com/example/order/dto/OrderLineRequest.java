package com.example.order.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderLineRequest {
    @NotNull
    private Long productId;
    @Min(1)
    private int quantity;
    @NotNull
    @Min(0)
    private Double unitPrice;

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
}