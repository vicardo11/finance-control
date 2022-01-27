package it.sosinski.financecontrol.core.exception;

public class RoleException extends FinanceControlException {
    public RoleException(String message) {
        super(message);
    }

    public RoleException(String message, Throwable cause) {
        super(message, cause);
    }
}
