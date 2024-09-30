package exceptions;

public class CurrencyRateParsingException extends Exception {
    public CurrencyRateParsingException(String message) {
        super(message);
    }

    public CurrencyRateParsingException(Throwable cause) {
        super(cause);
    }

    public CurrencyRateParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}

