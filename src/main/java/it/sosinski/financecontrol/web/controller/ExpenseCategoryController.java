package it.sosinski.financecontrol.web.controller;

import it.sosinski.financecontrol.core.exception.ExpenseCategoryAlreadyExists;
import it.sosinski.financecontrol.service.ExpenseCategoryService;
import it.sosinski.financecontrol.web.dto.ExpenseCategoryDto;
import it.sosinski.financecontrol.web.dto.NewExpenseCategoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static it.sosinski.financecontrol.web.controller.ControllerConstants.EXPENSE_CATEGORIES_URL;

@RestController
@RequestMapping(EXPENSE_CATEGORIES_URL)
public class ExpenseCategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseCategoryController.class);

    private final ExpenseCategoryService expenseCategoryService;

    public ExpenseCategoryController(ExpenseCategoryService expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseCategoryDto>> list() {
        LOGGER.info("list()");

        List<ExpenseCategoryDto> expenseCategoryDtos = expenseCategoryService.list();

        LOGGER.info("list() = " + expenseCategoryDtos);
        return new ResponseEntity<>(expenseCategoryDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExpenseCategoryDto> create(@RequestBody NewExpenseCategoryDto newExpenseCategoryDto) throws ExpenseCategoryAlreadyExists {
        LOGGER.info("create(" + newExpenseCategoryDto + ")");

        ExpenseCategoryDto expenseCategoryDto = expenseCategoryService.create(newExpenseCategoryDto);

        LOGGER.info("create(...) = " + expenseCategoryDto);
        return new ResponseEntity<>(expenseCategoryDto, HttpStatus.CREATED);
    }

}
