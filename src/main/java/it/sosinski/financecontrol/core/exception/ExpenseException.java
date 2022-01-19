package it.sosinski.financecontrol.core.exception;

public class ExpenseException extends FinanceControlException{
    public ExpenseException(String message) {
        super(message);
    }

    public ExpenseException(String message, Throwable cause) {
        super(message, cause);
    }
}
