package it.sosinski.financecontrol.service;

import it.sosinski.financecontrol.repository.ExpenseCategoryRepository;
import it.sosinski.financecontrol.repository.entity.ExpenseCategoryEntity;
import it.sosinski.financecontrol.service.mapper.ExpenseCategoryMapper;
import it.sosinski.financecontrol.web.dto.ExpenseCategoryDto;
import it.sosinski.financecontrol.web.dto.NewExpenseCategoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExpenseCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseCategoryService.class);

    private final ExpenseCategoryRepository expenseRepository;
    private final ExpenseCategoryMapper expenseCategoryMapper;

    public ExpenseCategoryService(ExpenseCategoryRepository expenseRepository, ExpenseCategoryMapper expenseCategoryMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseCategoryMapper = expenseCategoryMapper;
    }

    public ExpenseCategoryDto create(NewExpenseCategoryDto newExpenseCategoryDto) {
        LOGGER.info("create(" + newExpenseCategoryDto + ")");

        // TODO: Check if category with given name already exists
        // TODO: If so, throw message, that category already exists
        ExpenseCategoryEntity expenseCategoryEntity =
                expenseCategoryMapper.fromNewExpenseCategoryDtoToEntity(newExpenseCategoryDto);
        ExpenseCategoryEntity savedExpenseCategoryEntity = expenseRepository.save(expenseCategoryEntity);
        ExpenseCategoryDto expenseCategoryDto = expenseCategoryMapper.fromEntityToDto(savedExpenseCategoryEntity);

        LOGGER.info("create(" + expenseCategoryDto + ")");
        return expenseCategoryDto;
    }
}
