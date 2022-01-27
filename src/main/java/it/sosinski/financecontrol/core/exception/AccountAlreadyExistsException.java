package it.sosinski.financecontrol.core.exception;

public class AccountAlreadyExistsException extends AccountException {
    public AccountAlreadyExistsException(String message) {
        super(message);
    }

    public AccountAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
