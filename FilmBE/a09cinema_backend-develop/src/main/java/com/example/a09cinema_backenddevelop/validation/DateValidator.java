package com.example.a09cinema_backenddevelop.validation;

import com.example.a09cinema_backenddevelop.model.entity.Account;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator implements ConstraintValidator<DateFormat, LocalDate> {
    @Override
    public void initialize(DateFormat constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        LocalDate now = LocalDate.now();
        boolean isRetry = false;

        LocalDate birthDay = value;
        LocalDate before12Years = now.minusYears(12);
        LocalDate after80Years = now.minusYears(80);
        if (birthDay.isBefore(before12Years) && birthDay.isAfter(after80Years)) {
            isRetry=true;
        }
        return isRetry;
    }
}
