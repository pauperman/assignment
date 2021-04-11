package com.mycompany.assignment.model.output;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class City {

    private String id;
    private String name;
    private String countryCode;
    private String district;
    private BigDecimal population;
}
