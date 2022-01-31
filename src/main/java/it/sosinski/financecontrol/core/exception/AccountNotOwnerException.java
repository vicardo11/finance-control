package it.sosinski.financecontrol.core.exception;

public class AccountNotOwnerException extends AccountException {
    public AccountNotOwnerException(String message) {
        super(message);
    }

    public AccountNotOwnerException(String message, Throwable cause) {
        super(message, cause);
    }
}
