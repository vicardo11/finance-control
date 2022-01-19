package it.sosinski.financecontrol.core.exception;

public class FinanceControlException extends Exception{

    public FinanceControlException(String message) {
        super(message);
    }

    public FinanceControlException(String message, Throwable cause) {
        super(message, cause);
    }
}
