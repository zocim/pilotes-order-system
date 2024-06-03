/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.dtos;

import lombok.Data;

/**
 * @author Created by Maneva.
 * @since 2.6.24.
 */
@Data
public class AddressDTO {
    private String street;
    private String postcode;
    private String city;
    private String country;
}
