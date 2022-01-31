package it.sosinski.financecontrol.service;

import it.sosinski.financecontrol.core.exception.AccountNotFoundException;
import it.sosinski.financecontrol.core.exception.ExpenseCategoryNotFoundException;
import it.sosinski.financecontrol.core.exception.ExpenseNotFoundException;
import it.sosinski.financecontrol.repository.ExpenseCategoryRepository;
import it.sosinski.financecontrol.repository.ExpenseRepository;
import it.sosinski.financecontrol.repository.entity.Account;
import it.sosinski.financecontrol.repository.entity.Expense;
import it.sosinski.financecontrol.repository.entity.ExpenseCategory;
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
    private final AccountService accountService;

    public ExpenseService(ExpenseCategoryRepository expenseCategoryRepository, ExpenseRepository expenseRepository,
                          ExpenseMapper expenseMapper, AccountService accountService) {
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
        this.accountService = accountService;
    }

    public List<ExpenseDto> listByAccount(String email) throws AccountNotFoundException {
        LOGGER.info("listByAccount()");

        Account account = accountService.findByEmail(email);
        List<Expense> expenseEntities = expenseRepository.findAllByAccount_AccountId(account.getAccountId());
        List<ExpenseDto> expenseDtos = expenseMapper.fromEntitiesToDtos(expenseEntities);

        LOGGER.info("listByAccount() = " + expenseDtos);
        return expenseDtos;
    }

    public ExpenseDto read(Long expenseId) throws ExpenseNotFoundException {
        LOGGER.info("read(" + expenseId + ")");

        Optional<Expense> optionalEvent = expenseRepository.findById(expenseId);
        Expense expense = optionalEvent.orElseThrow(
                () -> new ExpenseNotFoundException(expenseId));
        ExpenseDto expenseDto = expenseMapper.fromEntityToDto(expense);

        LOGGER.info("read(...) = " + expenseDto);
        return expenseDto;
    }

    public ExpenseDto create(NewExpenseDto newExpenseDto) throws ExpenseCategoryNotFoundException {
        LOGGER.info("create(" + newExpenseDto + ")");

        ExpenseCategory expenseCategory = readExpenseCategoryById(newExpenseDto.getExpenseCategoryId());

        Expense expense = expenseMapper.fromNewDtoToEntity(newExpenseDto);

        expenseCategory.addExpense(expense);

        Expense savedExpense = expenseRepository.save(expense);

        expenseCategoryRepository.save(expenseCategory);

        ExpenseDto expenseDto = expenseMapper.fromEntityToDto(savedExpense);

        LOGGER.info("create(" + expenseDto + ")");
        return expenseDto;
    }

    public void delete(Long expenseId) throws ExpenseNotFoundException, ExpenseCategoryNotFoundException {
        LOGGER.info("delete(" + expenseId + ")");

        Expense expense = readExpenseById(expenseId);
        Long expenseCategoryId = expense.getExpenseCategory().getExpenseCategoryId();
        ExpenseCategory expenseCategory = readExpenseCategoryById(expenseCategoryId);
        expenseCategory.deleteExpense(expense);

        expenseCategoryRepository.save(expenseCategory);
        expenseRepository.delete(expense);

        LOGGER.info("delete(...)");
    }

    private ExpenseCategory readExpenseCategoryById(Long expenseCategoryId) throws ExpenseCategoryNotFoundException {
        Optional<ExpenseCategory> expenseCategoryOptional = expenseCategoryRepository.findById(expenseCategoryId);
        return expenseCategoryOptional.orElseThrow(
                () -> new ExpenseCategoryNotFoundException(expenseCategoryId)
        );
    }

    private Expense readExpenseById(Long expenseId) throws ExpenseNotFoundException {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
        return expenseOptional.orElseThrow(
                () -> new ExpenseNotFoundException(expenseId)
        );
    }
}
