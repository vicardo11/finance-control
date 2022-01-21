package it.sosinski.financecontrol.service;

import it.sosinski.financecontrol.repository.ExpenseCategoryRepository;
import it.sosinski.financecontrol.repository.entity.ExpenseCategory;
import it.sosinski.financecontrol.service.mapper.ExpenseCategoryMapper;
import it.sosinski.financecontrol.web.dto.ExpenseCategoryDto;
import it.sosinski.financecontrol.web.dto.NewExpenseCategoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseCategoryService.class);

    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final ExpenseCategoryMapper expenseCategoryMapper;

    public ExpenseCategoryService(ExpenseCategoryRepository expenseCategoryRepository, ExpenseCategoryMapper expenseCategoryMapper) {
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.expenseCategoryMapper = expenseCategoryMapper;
    }

    public List<ExpenseCategoryDto> list() {
        LOGGER.info("list()");

        List<ExpenseCategory> expenseCategories = expenseCategoryRepository.findAll();
        List<ExpenseCategoryDto> expenseCategoryDtos = expenseCategoryMapper.fromEntitiesToDtos(expenseCategories);

        LOGGER.info("list() = " + expenseCategoryDtos);
        return expenseCategoryDtos;
    }

    public ExpenseCategoryDto create(NewExpenseCategoryDto newExpenseCategoryDto) {
        LOGGER.info("create(" + newExpenseCategoryDto + ")");

        // TODO: Check if category with given name already exists
        // TODO: If so, throw message, that category already exists
        ExpenseCategory expenseCategory =
                expenseCategoryMapper.fromNewExpenseCategoryDtoToEntity(newExpenseCategoryDto);
        ExpenseCategory savedExpenseCategory = expenseCategoryRepository.save(expenseCategory);
        ExpenseCategoryDto expenseCategoryDto = expenseCategoryMapper.fromEntityToDto(savedExpenseCategory);

        LOGGER.info("create(" + expenseCategoryDto + ")");
        return expenseCategoryDto;
    }
}
