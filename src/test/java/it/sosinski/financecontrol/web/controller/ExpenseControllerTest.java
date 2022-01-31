package it.sosinski.financecontrol.web.controller;

import it.sosinski.financecontrol.core.exception.ExpenseNotFoundException;
import it.sosinski.financecontrol.service.ExpenseService;
import it.sosinski.financecontrol.web.dto.ExpenseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpenseControllerTest {

    public static final long EXPENSE_ID_1 = 1L;

    @Mock
    private ExpenseService expenseService;
    @InjectMocks
    private ExpenseController expenseController;

    @Test
    void givenExpenseDto_whenRead_thenReadExpenseDtoNotNull() throws ExpenseNotFoundException {
        //Given
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setExpenseId(EXPENSE_ID_1);

        //When
        when(expenseService.read(EXPENSE_ID_1)).thenReturn(expenseDto);
        ExpenseDto readExpenseDto = expenseController.read(EXPENSE_ID_1);

        //Then
        assertNotNull(readExpenseDto);
    }

    @Test
    void givenExpenseDto_whenRead_thenReadExpenseDtoIdEquals() throws ExpenseNotFoundException {
        //Given
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setExpenseId(EXPENSE_ID_1);

        //When
        when(expenseService.read(EXPENSE_ID_1)).thenReturn(expenseDto);
        ExpenseDto readExpenseDto = expenseController.read(EXPENSE_ID_1);

        //Then
        assertEquals(EXPENSE_ID_1, readExpenseDto.getExpenseId(), "Found ExpenseDto.id is not equal to: " + EXPENSE_ID_1);
    }

}