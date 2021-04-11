package com.mycompany.assignment.model.input;

import java.math.BigDecimal;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CityForm {

    @Size(min = 1, max = 35)
    private String name;
    @Size(min = 3, max = 3)
    private String countryCode;
    @Size(min = 1, max = 20)
    private String district;
    private BigDecimal population;
}
