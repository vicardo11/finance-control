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

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

        Account account = readAccountByEmail(email);
        List<Expense> expenseEntities = listExpensesByAccount(account);
        List<ExpenseDto> expenseDtos = mapFromExpensesToExpenseDtos(expenseEntities);

        LOGGER.info("listByAccount() = " + expenseDtos);
        return expenseDtos;
    }

    public ExpenseDto read(Long expenseId) throws ExpenseNotFoundException {
        LOGGER.info("read(" + expenseId + ")");

        Expense expense = readExpenseById(expenseId);
        ExpenseDto expenseDto = mapExpenseToExpenseDto(expense);

        LOGGER.info("read(...) = " + expenseDto);
        return expenseDto;
    }

    public ExpenseDto create(NewExpenseDto newExpenseDto, String accountEmail) throws ExpenseCategoryNotFoundException, AccountNotFoundException {
        LOGGER.info("create(" + newExpenseDto + ")");

        Account account = readAccountByEmail(accountEmail);
        ExpenseCategory expenseCategory = readExpenseCategoryById(newExpenseDto.getExpenseCategoryId());

        Expense expense = mapNewExpenseDtoToExpense(newExpenseDto);

        expense.setAccount(account);
        expense.setExpenseCategory(expenseCategory);

        Expense savedExpense = saveExpense(expense);

        ExpenseDto expenseDto = mapExpenseToExpenseDto(savedExpense);

        LOGGER.info("create(" + expenseDto + ")");
        return expenseDto;
    }

    public void delete(Long expenseId) throws ExpenseNotFoundException, ExpenseCategoryNotFoundException {
        LOGGER.info("delete(" + expenseId + ")");

        Expense expense = readExpenseById(expenseId);
        ExpenseCategory expenseCategory = readExpenseCategoryById(expense.getExpenseCategory().getExpenseCategoryId());

        expenseCategory.deleteExpense(expense);

        saveExpenseCategory(expenseCategory);
        deleteExpense(expense);

        LOGGER.info("delete(...)");
    }

    private void deleteExpense(Expense expense) {
        expenseRepository.delete(expense);
    }

    private List<ExpenseDto> mapFromExpensesToExpenseDtos(List<Expense> expenseEntities) {
        return expenseMapper.fromEntitiesToDtos(expenseEntities);
    }

    private Expense mapNewExpenseDtoToExpense(NewExpenseDto newExpenseDto) {
        return expenseMapper.fromNewDtoToEntity(newExpenseDto);
    }

    private ExpenseDto mapExpenseToExpenseDto(Expense savedExpense) {
        return expenseMapper.fromEntityToDto(savedExpense);
    }

    private List<Expense> listExpensesByAccount(Account account) {
        return expenseRepository.findAllByAccount_AccountId(account.getAccountId());
    }

    private ExpenseCategory saveExpenseCategory(ExpenseCategory expenseCategory) {
        return expenseCategoryRepository.save(expenseCategory);
    }

    private Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    private Account readAccountByEmail(String accountEmail) throws AccountNotFoundException {
        return accountService.findByEmail(accountEmail);
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
