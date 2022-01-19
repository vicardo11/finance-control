package it.sosinski.financecontrol.web.controller;

import it.sosinski.financecontrol.service.ExpenseService;
import it.sosinski.financecontrol.web.dto.ExpenseDto;
import it.sosinski.financecontrol.web.dto.NewExpenseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
    protected List<ExpenseDto> list() {
        LOGGER.info("list()");

        List<ExpenseDto> expenseDtos = expenseService.list();

        LOGGER.info("list() = " + expenseDtos);
        return expenseDtos;
    }

    @PostMapping
    protected ExpenseDto create(@RequestBody NewExpenseDto newExpenseDto) {
        LOGGER.info("newExpense(" + newExpenseDto + ")");

        ExpenseDto expenseDto = expenseService.create(newExpenseDto);

        LOGGER.info("newExpense(...) = " + expenseDto);
        return expenseDto;
    }

}
