package it.sosinski.financecontrol.web.controller;

import it.sosinski.financecontrol.core.exception.AccountNotFoundException;
import it.sosinski.financecontrol.core.exception.AccountNotOwnerException;
import it.sosinski.financecontrol.core.exception.ExpenseCategoryNotFoundException;
import it.sosinski.financecontrol.core.exception.ExpenseNotFoundException;
import it.sosinski.financecontrol.logging.LogInfo;
import it.sosinski.financecontrol.service.AuthenticationService;
import it.sosinski.financecontrol.service.ExpenseService;
import it.sosinski.financecontrol.web.dto.ExpenseDto;
import it.sosinski.financecontrol.web.dto.NewExpenseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static it.sosinski.financecontrol.web.controller.ControllerConstants.EXPENSES_URL;

@RestController
@RequestMapping(value = EXPENSES_URL)
class ExpenseController {

    private final ExpenseService expenseService;
    private final AuthenticationService authenticationService;

    ExpenseController(ExpenseService expenseService, AuthenticationService authenticationService) {
        this.expenseService = expenseService;
        this.authenticationService = authenticationService;
    }

    @LogInfo
    @GetMapping
    protected ResponseEntity<List<ExpenseDto>> listByAccount(Principal principal) throws AccountNotFoundException {

        List<ExpenseDto> expenseDtos = expenseService.listByAccount(principal.getName());

        return new ResponseEntity<>(expenseDtos, HttpStatus.OK);
    }

    @LogInfo
    @GetMapping("/{id}")
    protected ResponseEntity<ExpenseDto> read(@PathVariable(name = "id") Long id, Principal principal) throws ExpenseNotFoundException, AccountNotFoundException, AccountNotOwnerException {

        boolean authenticated = authenticationService.isAuthenticated(id, principal.getName());

        ExpenseDto expenseDto;
        if (authenticated) {
            expenseDto = expenseService.read(id);
        } else {
            throw new AccountNotOwnerException("Account is not an owner of an expense with id: " + id);
        }

        return new ResponseEntity<>(expenseDto, HttpStatus.OK);
    }

    @LogInfo
    @PostMapping
    protected ResponseEntity<ExpenseDto> create(@RequestBody NewExpenseDto newExpenseDto, Principal principal) throws ExpenseCategoryNotFoundException, AccountNotFoundException {

        String email = principal.getName();

        ExpenseDto expenseDto = expenseService.create(newExpenseDto, email);

        return new ResponseEntity<>(expenseDto, HttpStatus.CREATED);
    }

    @LogInfo
    @DeleteMapping("/{id}")
    protected ResponseEntity<String> delete(@PathVariable(name = "id") Long id, Principal principal) throws ExpenseNotFoundException, ExpenseCategoryNotFoundException, AccountNotOwnerException, AccountNotFoundException {

        boolean authenticated = authenticationService.isAuthenticated(id, principal.getName());

        if (authenticated) {
            expenseService.delete(id);
        } else {
            throw new AccountNotOwnerException("Account is not an owner of an expense with id: " + id);
        }

        return new ResponseEntity<>("Expense correctly deleted", HttpStatus.OK);
    }

}
