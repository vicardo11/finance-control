package it.sosinski.financecontrol.service;

import it.sosinski.financecontrol.core.exception.ExpenseNotFoundException;
import it.sosinski.financecontrol.repository.ExpenseRepository;
import it.sosinski.financecontrol.repository.entity.ExpenseEntity;
import it.sosinski.financecontrol.service.mapper.ExpenseMapper;
import it.sosinski.financecontrol.web.dto.ExpenseDto;
import it.sosinski.financecontrol.web.dto.NewExpenseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseService.class);

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseService(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }

    public List<ExpenseDto> list() {
        LOGGER.info("expenseDtos()");

        List<ExpenseEntity> expenseEntities = expenseRepository.findAll();
        List<ExpenseDto> expenseDtos = expenseMapper.fromEntitiesToDtos(expenseEntities);

        LOGGER.info("expenseDtos()");
        return expenseDtos;
    }

    public ExpenseDto read(Long expenseId) throws ExpenseNotFoundException {
        LOGGER.info("read(" + expenseId + ")");

        Optional<ExpenseEntity> optionalEventEntity = expenseRepository.findById(expenseId);
        ExpenseEntity expenseEntity = optionalEventEntity.orElseThrow(
                () -> new ExpenseNotFoundException("Expense not found for id: " + expenseId));
        ExpenseDto expenseDto = expenseMapper.fromEntityToDto(expenseEntity);

        LOGGER.info("read(...) = " + expenseDto);
        return expenseDto;
    }

    public ExpenseDto create(NewExpenseDto newExpenseDto) {
        LOGGER.info("newExpense(" + newExpenseDto + ")");

        ExpenseEntity expenseEntity = expenseMapper.fromNewDtoToEntity(newExpenseDto);
        ExpenseEntity savedExpenseEntity = expenseRepository.save(expenseEntity);
        ExpenseDto expenseDto = expenseMapper.fromEntityToDto(savedExpenseEntity);

        LOGGER.info("newExpense(" + expenseDto + ")");
        return expenseDto;
    }
}
