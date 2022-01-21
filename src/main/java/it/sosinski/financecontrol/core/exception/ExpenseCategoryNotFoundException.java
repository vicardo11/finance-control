package it.sosinski.financecontrol.core.exception;

public class ExpenseCategoryNotFoundException extends ExpenseCategoryException{
    public ExpenseCategoryNotFoundException(Long id) {
        super("Expense not found for id: " + id);
    }

    public ExpenseCategoryNotFoundException(Long id, Throwable cause) {
        super("Expense not found for id: " + id, cause);
    }
}
