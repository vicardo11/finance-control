package it.sosinski.financecontrol.service;

import it.sosinski.financecontrol.core.exception.ExpenseCategoryNotFoundException;
import it.sosinski.financecontrol.core.exception.ExpenseNotFoundException;
import it.sosinski.financecontrol.repository.ExpenseCategoryRepository;
import it.sosinski.financecontrol.repository.ExpenseRepository;
import it.sosinski.financecontrol.repository.entity.ExpenseCategoryEntity;
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

    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseService(ExpenseCategoryRepository expenseCategoryRepository, ExpenseRepository expenseRepository, ExpenseMapper expenseMapper) {
        this.expenseCategoryRepository = expenseCategoryRepository;
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
                () -> new ExpenseNotFoundException(expenseId));
        ExpenseDto expenseDto = expenseMapper.fromEntityToDto(expenseEntity);

        LOGGER.info("read(...) = " + expenseDto);
        return expenseDto;
    }

    public ExpenseDto create(NewExpenseDto newExpenseDto) throws ExpenseCategoryNotFoundException {
        LOGGER.info("newExpense(" + newExpenseDto + ")");

        ExpenseCategoryEntity expenseCategoryEntity = readExpenseCategoryEntityById(newExpenseDto.getExpenseCategoryId());

        ExpenseEntity expenseEntity = expenseMapper.fromNewDtoToEntity(newExpenseDto);

        expenseCategoryEntity.addExpense(expenseEntity);

        ExpenseEntity savedExpenseEntity = expenseRepository.save(expenseEntity);

        expenseCategoryRepository.save(expenseCategoryEntity);

        ExpenseDto expenseDto = expenseMapper.fromEntityToDto(savedExpenseEntity);

        LOGGER.info("newExpense(" + expenseDto + ")");
        return expenseDto;
    }

    public void delete(Long expenseId) throws ExpenseNotFoundException {
        LOGGER.info("delete(" + expenseId + ")");

        Optional<ExpenseEntity> expenseEntityOptional = expenseRepository.findById(expenseId);
        ExpenseEntity expenseEntity = expenseEntityOptional.orElseThrow(
                () -> new ExpenseNotFoundException(expenseId));
        expenseRepository.delete(expenseEntity);

        LOGGER.info("delete(...)");
    }

    private ExpenseCategoryEntity readExpenseCategoryEntityById(Long expenseCategoryId) throws ExpenseCategoryNotFoundException {
        Optional<ExpenseCategoryEntity> expenseCategoryEntityOptional = expenseCategoryRepository.findById(expenseCategoryId);
        return expenseCategoryEntityOptional.orElseThrow(
                () -> new ExpenseCategoryNotFoundException(expenseCategoryId)
        );
    }
}
