package it.sosinski.financecontrol.core.exception;

public class ExpenseCategoryAlreadyExists extends ExpenseCategoryException {
    public ExpenseCategoryAlreadyExists(String message) {
        super(message);
    }

    public ExpenseCategoryAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}
