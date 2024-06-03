/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.validations;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Created by Maneva.
 * @since 2.6.24.
 */
public class PilotesValidator implements ConstraintValidator<ValidPilotes, Integer> {
    private static final List<Integer> VALID_PILOTES = Arrays.asList(5, 10, 15);

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return VALID_PILOTES.contains(value);
    }
}
