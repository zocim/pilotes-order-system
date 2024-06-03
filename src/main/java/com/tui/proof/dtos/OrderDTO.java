/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.dtos;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author Created by Maneva.
 * @since 2.6.24.
 */
@Data
public class OrderDTO {
    private String number;
    private AddressDTO deliveryAddress;
    private int pilotes;
    private double orderTotal;
    private ClientDTO clientDTO;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
