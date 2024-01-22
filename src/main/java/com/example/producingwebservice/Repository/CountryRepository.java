package com.example.producingwebservice.Repository;

import com.example.producingwebservice.EntityDTO.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, String> {
    CountryEntity findCountryByName(String name);

    List<CountryEntity> findAll();
}
