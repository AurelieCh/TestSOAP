package com.example.producingwebservice.EndPoint;

import com.example.producingwebservice.CountryService.CountryService;
import io.spring.guides.gs_producing_web_service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndPoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    @Autowired
    private CountryService countryService;

    /**
     * Ajoute un pays dans la base,
     * des erreurs gérées sinon.
     *
     * @param request
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCountryRequest")
    @ResponsePayload
    public AddCountryResponse addCountry(@RequestPayload AddCountryRequest request) {
        return this.countryService.addCountry(request);
    }

    /**
     * Retourne le pays avec le nom passé en paramètre,
     * des erreurs gérées sinon.
     *
     * @param request
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        return this.countryService.getCountry(request.getName());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCountryRequest")
    @ResponsePayload
    public GetAllCountryResponse getAllCountry() {
        return this.countryService.getAllCountry();
    }
}