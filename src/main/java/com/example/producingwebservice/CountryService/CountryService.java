package com.example.producingwebservice.CountryService;

import com.example.producingwebservice.EntityDTO.CountryEntity;
import com.example.producingwebservice.EntityDTO.CurrencyEntity;
import com.example.producingwebservice.Repository.CountryRepository;
import io.spring.guides.gs_producing_web_service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;

    public AddCountryResponse addCountry(AddCountryRequest country){
        AddCountryResponse response = new AddCountryResponse();
        if(country.getCapital().isEmpty() || country.getName().isEmpty() ||
                country.getPopulation() == 0){
            response.setErrorCode(404);
            response.setErrorMessage("Erreur, mauvaise données d'entrée (っ °Д °;)っ");
            return response;
        }
        CountryEntity test = countryRepository.findCountryByName(country.getName());
        if(test != null){
            response.setErrorCode(409);
            response.setErrorMessage("Erreur, un pays avec ce nom existe déjà (╯°□°）╯︵ ┻━┻");
            return response;
        }
        CountryEntity nouveau = CountryEntity.builder()
                .name(country.getName())
                .capital(country.getCapital())
                .population(country.getPopulation())
                .currency(CurrencyEntity.valueOf(country.getCurrency().toString()))
                .build();
        countryRepository.save(nouveau);

        response.setName(country.getName());
        response.setCapital(country.getCapital());
        response.setPopulation(country.getPopulation());
        response.setCurrency(country.getCurrency());
        response.setErrorCode(200);

        return response;
    }

    public GetCountryResponse getCountry(String name){
        GetCountryResponse response = new GetCountryResponse();
        if(name.isEmpty()){
            response.setErrorCode(404);
            response.setErrorMessage("Erreur, mauvaise données d'entrée (っ °Д °;)っ");
            return response;
        } else {
            CountryEntity rep = countryRepository.findCountryByName(name);
            if(rep == null){
                response.setErrorCode(204);
                response.setErrorMessage("Erreur, aucune donnée w(ﾟДﾟ)w");
                return response;
            } else {
                Country repp = new Country();
                repp.setName(rep.getName());
                repp.setCapital(rep.getCapital());
                repp.setPopulation(rep.getPopulation());
                repp.setCurrency(Currency.valueOf(rep.getCurrency().toString()));

                response.setCountry(repp);
                response.setErrorCode(200);

                return response;
            }
        }
    }

    public GetAllCountryResponse getAllCountry(){
        GetAllCountryResponse response = new GetAllCountryResponse();
        List<CountryEntity> reps = countryRepository.findAll();
        if(reps.isEmpty()){
            response.setErrorCode(204);
            response.setErrorMessage("Erreur, aucune donnée w(ﾟДﾟ)w");
            return response;
        } else {
            for (CountryEntity ce : reps) {
                Country c = new Country();
                c.setName(ce.getName());
                c.setCapital(ce.getCapital());
                c.setPopulation(ce.getPopulation());
                c.setCurrency(Currency.valueOf(ce.getCurrency().toString()));
                response.getCountry().add(c);
            }
            response.setErrorCode(200);

            return response;
        }
    }
}
