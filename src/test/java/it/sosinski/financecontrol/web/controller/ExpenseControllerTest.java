package it.sosinski.financecontrol.web.controller;

import it.sosinski.financecontrol.service.ExpenseService;
import it.sosinski.financecontrol.web.dto.ExpenseDto;
import it.sosinski.financecontrol.web.dto.NewExpenseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpenseControllerTest {

    public static final long EXPENSE_ID_1 = 1L;

    @Mock
    private ExpenseService expenseService;
    @InjectMocks
    private ExpenseController expenseController;

    @Test
    void given_whenList_thenResultListSizeEquals() throws Exception {
        //Given
        List<ExpenseDto> expenseDtos = List.of(
                new ExpenseDto(),
                new ExpenseDto()
        );

        when(expenseService.list()).thenReturn(expenseDtos);

        //When
        List<ExpenseDto> resultList = expenseController.list();

        //Then
        assertEquals(expenseDtos.size(), resultList.size(), "Result list isn't equal to: " + expenseDtos);
    }

    @Test
    void given_whenCreate_thenResultExpenseDtoNotNull() throws Exception {
        //Given
        NewExpenseDto newExpenseDto = new NewExpenseDto();

        ExpenseDto expenseDto = new ExpenseDto();

        when(expenseService.create(any())).thenReturn(expenseDto);

        //When
        ExpenseDto resultExpenseDto = expenseController.create(newExpenseDto);

        //Then
        assertNotNull(resultExpenseDto, "Result expenseDto is null");
    }

    @Test
    void given_whenCreate_thenResultExpenseDtoIdEquals() throws Exception {
        //Given
        NewExpenseDto newExpenseDto = new NewExpenseDto();

        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setExpenseId(EXPENSE_ID_1);

        when(expenseService.create(any())).thenReturn(expenseDto);

        //When
        ExpenseDto resultExpenseDto = expenseController.create(newExpenseDto);

        //Then
        assertEquals(EXPENSE_ID_1, resultExpenseDto.getExpenseId(), "Result expenseDto.id isn't equal to: " + EXPENSE_ID_1);
    }
}