package it.sosinski.financecontrol.service;

import it.sosinski.financecontrol.core.exception.ExpenseCategoryAlreadyExists;
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

    public ExpenseCategoryDto create(NewExpenseCategoryDto newExpenseCategoryDto) throws ExpenseCategoryAlreadyExists {
        LOGGER.info("create(" + newExpenseCategoryDto + ")");

        ExpenseCategory expenseCategory =
                expenseCategoryMapper.fromNewExpenseCategoryDtoToEntity(newExpenseCategoryDto);
        if (categoryNameExists(expenseCategory.getName())) {
            throw new ExpenseCategoryAlreadyExists("ExpenseCategory already exists with name: " + expenseCategory.getName());
        }
        ExpenseCategory savedExpenseCategory = expenseCategoryRepository.save(expenseCategory);
        ExpenseCategoryDto expenseCategoryDto = expenseCategoryMapper.fromEntityToDto(savedExpenseCategory);

        LOGGER.info("create(" + expenseCategoryDto + ")");
        return expenseCategoryDto;
    }

    private boolean categoryNameExists(String expenseCategoryName) {
        return expenseCategoryRepository.findByName(expenseCategoryName).isPresent();
    }
}
