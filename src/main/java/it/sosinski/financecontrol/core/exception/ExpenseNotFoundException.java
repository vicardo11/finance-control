package it.sosinski.financecontrol.core.exception;

public class ExpenseNotFoundException extends ExpenseException{
    public ExpenseNotFoundException(Long id) {
        super("Expense not found for id: " + id);
    }

    public ExpenseNotFoundException(Long id, Throwable cause) {
        super("Expense not found for id: " + id, cause);
    }
}
