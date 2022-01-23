package it.sosinski.financecontrol.core.exception;

public class AccountNotFoundException extends AccountException{

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
