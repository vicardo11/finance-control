package it.sosinski.financecontrol.web.config;

import it.sosinski.financecontrol.core.api.ErrorResponse;
import it.sosinski.financecontrol.core.exception.FinanceControlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = FinanceControlException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse handler(Exception exception) {
        LOGGER.info("handler(" + exception + ")");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        LOGGER.info("handler(...) = " + errorResponse);
        return errorResponse;
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse runtimeHandler(Exception exception) {
        LOGGER.info("runtimeHandler(" + exception + ")");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        LOGGER.info("runtimeHandler(...) = " + errorResponse);
        return errorResponse;
    }
}
