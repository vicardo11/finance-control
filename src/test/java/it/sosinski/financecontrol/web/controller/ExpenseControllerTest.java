package it.sosinski.financecontrol.web.controller;

import it.sosinski.financecontrol.service.ExpenseService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExpenseControllerTest {

    public static final long EXPENSE_ID_1 = 1L;

    @Mock
    private ExpenseService expenseService;
    @InjectMocks
    private ExpenseController expenseController;

}