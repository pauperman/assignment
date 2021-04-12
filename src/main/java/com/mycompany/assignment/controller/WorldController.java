package com.mycompany.assignment.controller;

import com.mycompany.assignment.exception.CityNotFoundException;
import com.mycompany.assignment.exception.CountryNotFoundException;
import com.mycompany.assignment.exception.InvalidCountryException;
import com.mycompany.assignment.exception.LanguageNotFoundException;
import com.mycompany.assignment.model.input.CityForm;
import com.mycompany.assignment.model.output.City;
import com.mycompany.assignment.model.output.Country;
import com.mycompany.assignment.model.output.CountryDetailWithExtras;
import com.mycompany.assignment.model.output.Language;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface WorldController {

    @GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Country>> getCountryList(@RequestParam(required = false) String name, @RequestParam(required = false) String continent);

    @GetMapping(value = "/countries/{countryCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CountryDetailWithExtras> getCountry(@PathVariable String countryCode) throws CountryNotFoundException;

    @GetMapping(value = "/countries/{countryCode}/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<City>> getCountryCityList(@PathVariable String countryCode) throws CountryNotFoundException;

    @GetMapping(value = "/countries/{countryCode}/languages", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Language>> getCountryLanguageList(@PathVariable String countryCode) throws CountryNotFoundException;

    @GetMapping(value = "/countries/{countryCode}/languages/{language}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Language> getCountryLanguage(@PathVariable String countryCode, @PathVariable String language) throws CountryNotFoundException, LanguageNotFoundException;

    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<City>> getCityList(@RequestParam(required = false) String name, @RequestParam(required = false) String countryCode, @RequestParam(required = false) String district);

    @GetMapping(value = "/cities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<City> getCity(@PathVariable String id) throws CityNotFoundException;

    @DeleteMapping(value = "/cities/{id}")
    ResponseEntity<Void> deleteCity(@PathVariable String id) throws CityNotFoundException;

    @PutMapping(value = "/cities/{id}")
    ResponseEntity<Void> putCity(@PathVariable String id, @Valid @RequestBody CityForm cityForm) throws InvalidCountryException, CityNotFoundException;

    @PostMapping(value = "/cities")
    ResponseEntity<Void> postCity(@Valid @RequestBody CityForm cityForm) throws InvalidCountryException;
}
