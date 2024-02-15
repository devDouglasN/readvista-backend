package com.douglas.readvista.loan.validators;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BookLoanData(

        @NotNull
        Integer idBook,

        @NotNull
        Integer idCustomer,

        @NotNull @Future
        LocalDateTime date) {
}
