package it.sosinski.financecontrol.core.exception;

public class ExpenseNotFoundException extends ExpenseException{
    public ExpenseNotFoundException(String message) {
        super(message);
    }

    public ExpenseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
