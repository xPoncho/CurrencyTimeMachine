package models;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;


@Builder
@AllArgsConstructor(onConstructor_ = @__(@JsonCreator))
@FieldDefaults(level = AccessLevel.PRIVATE)
@Value
public class CurrencyRate {
    String numCode;
    String charCode;
    String nominal;
    String name;
    String value;
    String vunitRate;
}
