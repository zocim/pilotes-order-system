/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.exception;

/**
 * @author Created by Maneva.
 * @since 3.6.24.
 */
public class OrderApiException extends RuntimeException {
    public OrderApiException(String message) {
        super(message);
    }
}
