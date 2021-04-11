package com.mycompany.assignment.controller;

import com.mycompany.assignment.converter.CountryConverter;
import com.mycompany.assignment.exception.CityNotFoundException;
import com.mycompany.assignment.exception.CountryNotFoundException;
import com.mycompany.assignment.exception.InvalidCountryException;
import com.mycompany.assignment.exception.LanguageNotFoundException;
import com.mycompany.assignment.mapper.CityMapper;
import com.mycompany.assignment.mapper.CountryMapper;
import com.mycompany.assignment.mapper.LanguageMapper;
import com.mycompany.assignment.model.input.CityForm;
import com.mycompany.assignment.model.output.City;
import com.mycompany.assignment.model.output.Country;
import com.mycompany.assignment.model.output.CountryDetail;
import com.mycompany.assignment.model.output.CountryDetailWithExtras;
import com.mycompany.assignment.model.output.Language;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorldController {

    private final CountryMapper countryMapper;
    private final CityMapper cityMapper;
    private final LanguageMapper languageMapper;

    public WorldController(CountryMapper countryMapper, CityMapper cityMapper, LanguageMapper languageMapper) {
        this.countryMapper = countryMapper;
        this.cityMapper = cityMapper;
        this.languageMapper = languageMapper;
    }

    @GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Country>> getCountryList(@RequestParam(required = false) String name, @RequestParam(required = false) String continent) {
        return new ResponseEntity<>(countryMapper.findAll(name, continent), HttpStatus.OK);
    }

    @GetMapping(value = "/countries/{countryCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryDetailWithExtras> getCountry(@PathVariable String countryCode) throws CountryNotFoundException {
        CountryDetail country = countryMapper.findByCode(countryCode);
        if (country == null) {
            throw new CountryNotFoundException();
        } else {
            List<City> cityList = cityMapper.findByCountryCode(countryCode);
            List<Language> languageList = languageMapper.findByCountryCode(countryCode);
            CountryDetailWithExtras countryDetailWithExtras = CountryConverter.INSTANCE.toCountryDetailWithExtras(country, cityList, languageList);
            return new ResponseEntity<>(countryDetailWithExtras, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/countries/{countryCode}/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<City>> getCityList(@PathVariable String countryCode) throws CountryNotFoundException {
        if (countryMapper.findByCode(countryCode) == null) {
            throw new CountryNotFoundException();
        }
        return new ResponseEntity<>(cityMapper.findByCountryCode(countryCode), HttpStatus.OK);
    }

    @GetMapping(value = "/countries/{countryCode}/languages", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Language>> getLanguageList(@PathVariable String countryCode) throws CountryNotFoundException {
        if (countryMapper.findByCode(countryCode) == null) {
            throw new CountryNotFoundException();
        }
        return new ResponseEntity<>(languageMapper.findByCountryCode(countryCode), HttpStatus.OK);
    }

    @GetMapping(value = "/countries/{countryCode}/languages/{language}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Language> getLanguage(@PathVariable String countryCode, @PathVariable String language) throws CountryNotFoundException, LanguageNotFoundException {
        if (countryMapper.findByCode(countryCode) == null) {
            throw new CountryNotFoundException();
        }
        Language languageFound = languageMapper.findByCountryCodeAndLanguage(countryCode, language);
        if (languageFound == null) {
            throw new LanguageNotFoundException();
        }
        return new ResponseEntity<>(languageFound, HttpStatus.OK);
    }

    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<City>> getCityList(@RequestParam(required = false) String name, @RequestParam(required = false) String countryCode, @RequestParam(required = false) String district) {
        return new ResponseEntity<>(cityMapper.findAll(name, countryCode, district), HttpStatus.OK);
    }

    @GetMapping(value = "/cities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<City> getCity(@PathVariable String id) throws CityNotFoundException {
        City city = cityMapper.findById(id);
        if (city == null) {
            throw new CityNotFoundException();
        }
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cities/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable String id) throws CityNotFoundException {
        int affectedRows = cityMapper.delete(id);
        if (affectedRows == 0) {
            throw new CityNotFoundException();
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/cities/{id}")
    public ResponseEntity<Void> putCity(@PathVariable String id, @Valid @RequestBody CityForm cityForm) throws InvalidCountryException, CityNotFoundException {
        if (countryMapper.findByCode(cityForm.getCountryCode()) == null) {
            throw new InvalidCountryException();
        }
        int affectedRows = cityMapper.update(id, cityForm.getName(), cityForm.getCountryCode(), cityForm.getDistrict(), cityForm.getPopulation());
        if (affectedRows == 0) {
            throw new CityNotFoundException();
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/cities")
    public ResponseEntity<Void> postCity(@Valid @RequestBody CityForm cityForm) throws InvalidCountryException {
        if (countryMapper.findByCode(cityForm.getCountryCode()) == null) {
            throw new InvalidCountryException();
        }
        cityMapper.insert(cityForm.getName(), cityForm.getCountryCode(), cityForm.getDistrict(), cityForm.getPopulation());
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
