package it.sosinski.financecontrol.core.exception;

public class AccountException extends FinanceControlException{
    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
