package com.tui.proof.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.tui.proof.validations.ValidPilotes;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Table("orders")
public class Order {
    @Id
    private String number;
    @NotNull(message = "Delivery address cannot be null")
    private Address deliveryAddress;
    @ValidPilotes
    private int pilotes;
    @Min(value = 0, message = "order total cannot be negative")
    private double orderTotal;
    @NotNull(message = "Client cannot be null")
    private Client client;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTIme;
    private boolean updatable;
}
