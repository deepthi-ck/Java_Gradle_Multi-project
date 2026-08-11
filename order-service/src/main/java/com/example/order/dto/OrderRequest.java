package com.example.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class OrderRequest {
    @NotEmpty
    @Valid
    private List<OrderLineRequest> lines;

    public List<OrderLineRequest> getLines() { return lines; }
    public void setLines(List<OrderLineRequest> lines) { this.lines = lines; }
}