package it.sosinski.financecontrol.web.controller;

import it.sosinski.financecontrol.core.exception.ExpenseCategoryAlreadyExists;
import it.sosinski.financecontrol.logging.LogInfo;
import it.sosinski.financecontrol.service.ExpenseCategoryService;
import it.sosinski.financecontrol.web.dto.ExpenseCategoryDto;
import it.sosinski.financecontrol.web.dto.NewExpenseCategoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static it.sosinski.financecontrol.web.controller.ControllerConstants.EXPENSE_CATEGORIES_URL;

@RestController
@RequestMapping(EXPENSE_CATEGORIES_URL)
public class ExpenseCategoryController {

    private final ExpenseCategoryService expenseCategoryService;

    public ExpenseCategoryController(ExpenseCategoryService expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }

    @LogInfo
    @GetMapping
    public ResponseEntity<List<ExpenseCategoryDto>> list() {

        List<ExpenseCategoryDto> expenseCategoryDtos = expenseCategoryService.list();

        return new ResponseEntity<>(expenseCategoryDtos, HttpStatus.OK);
    }

    @LogInfo
    @PostMapping
    public ResponseEntity<ExpenseCategoryDto> create(@RequestBody NewExpenseCategoryDto newExpenseCategoryDto) throws ExpenseCategoryAlreadyExists {

        ExpenseCategoryDto expenseCategoryDto = expenseCategoryService.create(newExpenseCategoryDto);

        return new ResponseEntity<>(expenseCategoryDto, HttpStatus.CREATED);
    }

}
