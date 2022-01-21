package it.sosinski.financecontrol.core.exception;

public class ExpenseCategoryException extends FinanceControlException{
    public ExpenseCategoryException(String message) {
        super(message);
    }

    public ExpenseCategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
