package com.example.producingwebservice.EntityDTO;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CountryEntity {
    @jakarta.persistence.Id
    @Id
    private String name;
    private int population;
    private String capital;
    private CurrencyEntity currency;
}
