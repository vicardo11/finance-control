package it.sosinski.financecontrol.web.controller;

import it.sosinski.financecontrol.core.exception.AccountAlreadyExistsException;
import it.sosinski.financecontrol.core.exception.RoleNotFoundException;
import it.sosinski.financecontrol.logging.LogInfo;
import it.sosinski.financecontrol.service.AccountService;
import it.sosinski.financecontrol.web.dto.NewAccountDto;
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

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @LogInfo
    @PostMapping(REGISTRATION_SUB_URL)
    public ResponseEntity<String> register(@RequestBody NewAccountDto newAccountDto) throws RoleNotFoundException,
            AccountAlreadyExistsException {

        accountService.register(newAccountDto);

        return new ResponseEntity<>("Account created!", HttpStatus.CREATED);
    }
}
