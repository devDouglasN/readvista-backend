package com.douglas.readvista.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public record Address(String neighborhood, String CEP, String city, String complement, String number) {
}
