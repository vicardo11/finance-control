package it.sosinski.financecontrol.web.controller;

import it.sosinski.financecontrol.core.exception.AccountAlreadyExistsException;
import it.sosinski.financecontrol.core.exception.RoleNotFoundException;
import it.sosinski.financecontrol.service.AccountService;
import it.sosinski.financecontrol.web.dto.NewAccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static it.sosinski.financecontrol.web.controller.ControllerConstants.ACCOUNTS_URL;
import static it.sosinski.financecontrol.web.controller.ControllerConstants.REGISTRATION_SUB_URL;

@RestController
@RequestMapping(ACCOUNTS_URL)
public class AccountController {

    private final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(REGISTRATION_SUB_URL)
    public ResponseEntity<String> register(@RequestBody NewAccountDto newAccountDto) throws RoleNotFoundException,
            AccountAlreadyExistsException {
        LOGGER.info("register(" + newAccountDto + ")");

        accountService.register(newAccountDto);

        LOGGER.info("register(...)");
        return new ResponseEntity<>("Account created!", HttpStatus.CREATED);
    }
}
