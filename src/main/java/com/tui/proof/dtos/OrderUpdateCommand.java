/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.dtos;

import com.tui.proof.model.Address;

import lombok.Data;

/**
 * @author Created by Maneva.
 * @since 2.6.24.
 */
@Data
public class OrderUpdateCommand {
    private String number;
    private String street;
    private String postcode;
    private String city;
    private String country;
    private int pilotes;
    private String clientFirstName;
    private String clientLastName;
    private String clientTelephone;
}
