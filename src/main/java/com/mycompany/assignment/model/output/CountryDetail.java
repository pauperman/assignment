package com.mycompany.assignment.model.output;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDetail extends Country {

    protected String region;
    protected BigDecimal surfaceArea;
}
