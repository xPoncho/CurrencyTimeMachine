package parser;

import exceptions.CurrencyRateParsingException;
import models.CurrencyRate;

import java.util.List;

public interface CurrencyRateParser {

    List<CurrencyRate> parse(String ratesAsString) throws CurrencyRateParsingException;
}
