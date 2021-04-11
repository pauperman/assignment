package com.mycompany.assignment.model.output;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDetailWithExtras extends CountryDetail {

    private List<City> cities;
    private List<Language> languages;
}
