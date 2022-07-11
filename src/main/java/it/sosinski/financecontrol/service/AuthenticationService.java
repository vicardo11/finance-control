package it.sosinski.financecontrol.service;

import it.sosinski.financecontrol.core.exception.AccountNotFoundException;
import it.sosinski.financecontrol.core.exception.ExpenseNotFoundException;
import it.sosinski.financecontrol.logging.LogInfo;
import it.sosinski.financecontrol.repository.ExpenseRepository;
import it.sosinski.financecontrol.repository.entity.Account;
import it.sosinski.financecontrol.repository.entity.Expense;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final AccountService accountService;
    private final ExpenseRepository expenseRepository;

    public AuthenticationService(AccountService accountService, ExpenseRepository expenseRepository) {
        this.accountService = accountService;
        this.expenseRepository = expenseRepository;
    }

    @LogInfo
    public boolean isAuthenticated(Long expenseId, String email) throws AccountNotFoundException,
            ExpenseNotFoundException {

        Account account = accountService.findByEmail(email);
        Expense expense = findExpenseById(expenseId);
        boolean authenticated = expense.getAccount().getAccountId().equals(account.getAccountId());

        return authenticated;
    }

    private Expense findExpenseById(Long expenseId) throws ExpenseNotFoundException {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
        Expense expense = expenseOptional.orElseThrow(
                () -> new ExpenseNotFoundException(expenseId)
        );
        return expense;
    }
}
