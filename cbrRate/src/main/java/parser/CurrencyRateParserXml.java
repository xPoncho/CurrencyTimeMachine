package parser;

import exceptions.CurrencyRateParsingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import models.CurrencyRate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class CurrencyRateParserXml implements CurrencyRateParser{
    @Override
    public List<CurrencyRate> parse(String ratesAsString) throws CurrencyRateParsingException {

        List<CurrencyRate> rates = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        factory.setAttribute(XMLConstants.FEATURE_SECURE_PROCESSING, "true");

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            try {
                StringReader stringReader = new StringReader(ratesAsString);
                Document doc = builder.parse(new InputSource(stringReader));
                doc.getDocumentElement().normalize();

                NodeList currencyRates = doc.getElementsByTagName("Valute");

                for (int rateIndex = 0; rateIndex < currencyRates.getLength(); rateIndex++) {
                    Node node = currencyRates.item(rateIndex);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;

                        var rate = CurrencyRate.builder()
                                .numCode(getTagValue(element, "NumCode"))
                                .charCode(getTagValue(element, "CharCode"))
                                .nominal(getTagValue(element, "Nominal"))
                                .name(getTagValue(element, "Name"))
                                .value(getTagValue(element, "Value"))
                                .vunitRate(getTagValue(element, "VunitRate"))
                                .build();
                        rates.add(rate);
                    }
                }

            } catch (IOException | SAXException e) {
                throw new CurrencyRateParsingException("Error parsing XML", e);
            }

        } catch (Exception e) {
            log.error("Unexpected error during currency rate parsing: {}", e.getMessage(), e);
            throw new CurrencyRateParsingException("Unexpected error during currency rate parsing", e);
        }
        return rates;
    }

    private String getTagValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        return nodeList.getLength() > 0
                ? nodeList.item(0).getTextContent()
                : null;  // Или вернуть "" или дефолтное значение
    }

    private CurrencyRate parseValute(Element element) {
        return CurrencyRate.builder()
                .numCode(getTagValue(element, "NumCode"))
                .charCode(getTagValue(element, "CharCode"))
                .nominal(getTagValue(element, "Nominal"))
                .name(getTagValue(element, "Name"))
                .value(getTagValue(element, "Value"))
                .vunitRate(getTagValue(element, "VunitRate"))
                .build();
    }

}
