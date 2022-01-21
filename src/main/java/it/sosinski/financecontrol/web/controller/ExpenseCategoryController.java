package it.sosinski.financecontrol.web.controller;

import it.sosinski.financecontrol.service.ExpenseCategoryService;
import it.sosinski.financecontrol.web.dto.ExpenseCategoryDto;
import it.sosinski.financecontrol.web.dto.NewExpenseCategoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static it.sosinski.financecontrol.web.controller.ControllerConstants.EXPENSE_CATEGORIES_URL;

@RestController
@RequestMapping(EXPENSE_CATEGORIES_URL)
public class ExpenseCategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseCategoryController.class);

    private final ExpenseCategoryService expenseCategoryService;

    public ExpenseCategoryController(ExpenseCategoryService expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }

    @PostMapping
    public ExpenseCategoryDto create(@RequestBody NewExpenseCategoryDto newExpenseCategoryDto) {
        LOGGER.info("create(" + newExpenseCategoryDto + ")");

        ExpenseCategoryDto expenseCategoryDto = expenseCategoryService.create(newExpenseCategoryDto);

        LOGGER.info("create(...) = " + expenseCategoryDto);
        return expenseCategoryDto;
    }

}
