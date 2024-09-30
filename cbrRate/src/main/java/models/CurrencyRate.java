package models;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;


@Builder
@AllArgsConstructor(onConstructor_ = @__(@JsonCreator))
@FieldDefaults(level = AccessLevel.PRIVATE)
@Value
public class CurrencyRate {
    String numCode;
    String charCode;
    int nominal;
    String name;
    double value;
    double vunitRate;
}
