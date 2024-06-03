/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.events;

import com.tui.proof.model.Order;

import lombok.Data;

/**
 * @author Created by Maneva.
 * @since 2.6.24.
 */
@Data
public class OrderEvent {
    private String number;
    private OrderStatus status;
    private Order order;
}
