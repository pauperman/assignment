package com.mycompany.assignment.converter;

import com.mycompany.assignment.model.output.City;
import com.mycompany.assignment.model.output.CountryDetail;
import com.mycompany.assignment.model.output.CountryDetailWithExtras;
import com.mycompany.assignment.model.output.Language;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryConverter {

    CountryConverter INSTANCE = Mappers.getMapper(CountryConverter.class);

    @Mapping(source = "cityList", target = "cities")
    @Mapping(source = "languageList", target = "languages")
    CountryDetailWithExtras toCountryDetailWithExtras(CountryDetail countryDetail, List<City> cityList, List<Language> languageList);
}
