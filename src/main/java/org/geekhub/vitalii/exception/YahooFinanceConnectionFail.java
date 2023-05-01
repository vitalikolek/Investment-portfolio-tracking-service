package org.geekhub.vitalii.exception;

public class YahooFinanceConnectionFail extends RuntimeException {

    public YahooFinanceConnectionFail() {
        super();
    }

    public YahooFinanceConnectionFail(String message) {
        super(message);
    }

    public YahooFinanceConnectionFail(String message, Throwable cause) {
        super(message, cause);
    }
}
