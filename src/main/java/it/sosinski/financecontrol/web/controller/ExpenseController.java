package it.sosinski.financecontrol.web.controller;

import it.sosinski.financecontrol.core.exception.AccountNotFoundException;
import it.sosinski.financecontrol.core.exception.ExpenseCategoryNotFoundException;
import it.sosinski.financecontrol.core.exception.ExpenseNotFoundException;
import it.sosinski.financecontrol.service.ExpenseService;
import it.sosinski.financecontrol.web.dto.ExpenseDto;
import it.sosinski.financecontrol.web.dto.NewExpenseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static it.sosinski.financecontrol.web.controller.ControllerConstants.EXPENSES_URL;

@RestController
@RequestMapping(value = EXPENSES_URL)
class ExpenseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);

    private final ExpenseService expenseService;

    ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    protected List<ExpenseDto> listByAccount(Principal principal) throws AccountNotFoundException {
        LOGGER.info("listByAccount()");

        List<ExpenseDto> expenseDtos = expenseService.listByAccount(principal.getName());

        LOGGER.info("listByAccount() = " + expenseDtos);
        return expenseDtos;
    }

    @GetMapping("/{id}")
    protected ExpenseDto read(@PathVariable(name = "id") Long id) throws ExpenseNotFoundException {
        LOGGER.info("read(" + id + ")");

        ExpenseDto expenseDto = expenseService.read(id);

        LOGGER.info("read() = " + expenseDto);
        return expenseDto;
    }

    @PostMapping
    protected ExpenseDto create(@RequestBody NewExpenseDto newExpenseDto) throws ExpenseCategoryNotFoundException {
        LOGGER.info("create(" + newExpenseDto + ")");

        ExpenseDto expenseDto = expenseService.create(newExpenseDto);

        LOGGER.info("create(...) = " + expenseDto);
        return expenseDto;
    }

    @DeleteMapping("/{id}")
    protected ResponseEntity<String> delete(@PathVariable(name = "id") Long id) throws ExpenseNotFoundException, ExpenseCategoryNotFoundException {
        LOGGER.info("delete(" + id + ")");

        expenseService.delete(id);

        LOGGER.info("delete(...)");
        return new ResponseEntity<>("Expense correctly deleted", HttpStatus.OK);
    }

}
