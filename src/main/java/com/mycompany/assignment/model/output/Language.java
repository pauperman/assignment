package com.mycompany.assignment.model.output;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Language {

    private String language;
    private String isOfficial;
    private BigDecimal percentage;
}
