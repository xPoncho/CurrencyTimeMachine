package parser;

import models.CurrencyRate;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;

public class CurrencyRateParserXml implements CurrencyRateParser{
    @Override
    public List<CurrencyRate> parse(String ratesAsString) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        return null;
    }
}
