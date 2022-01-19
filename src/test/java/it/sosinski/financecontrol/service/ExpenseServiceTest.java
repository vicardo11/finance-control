package it.sosinski.financecontrol.service;

import it.sosinski.financecontrol.core.exception.ExpenseNotFoundException;
import it.sosinski.financecontrol.repository.ExpenseRepository;
import it.sosinski.financecontrol.repository.entity.ExpenseEntity;
import it.sosinski.financecontrol.service.mapper.ExpenseMapper;
import it.sosinski.financecontrol.web.dto.ExpenseDto;
import it.sosinski.financecontrol.web.dto.NewExpenseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    public static final long EXPENSE_ID_1 = 1L;

    @Mock
    private ExpenseRepository expenseRepository;
    @Mock
    private ExpenseMapper expenseMapper;
    @InjectMocks
    private ExpenseService expenseService ;

    @Test
    void given_whenRead_thenExpenseDtoNotNull() throws ExpenseNotFoundException {
        //Given
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setExpenseId(EXPENSE_ID_1);

        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setExpenseId(EXPENSE_ID_1);

        //When
        when(expenseRepository.findById(EXPENSE_ID_1)).thenReturn(Optional.of(expenseEntity));
        when(expenseMapper.fromEntityToDto(expenseEntity)).thenReturn(expenseDto);

        ExpenseDto readExpenseDto = expenseService.read(EXPENSE_ID_1);

        //Then
        assertNotNull(readExpenseDto);
    }

    @Test
    void given_whenRead_thenExpenseDtoIdEquals() throws ExpenseNotFoundException {
        //Given
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setExpenseId(EXPENSE_ID_1);

        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setExpenseId(EXPENSE_ID_1);

        //When
        when(expenseRepository.findById(EXPENSE_ID_1)).thenReturn(Optional.of(expenseEntity));
        when(expenseMapper.fromEntityToDto(expenseEntity)).thenReturn(expenseDto);

        ExpenseDto readExpenseDto = expenseService.read(EXPENSE_ID_1);

        //Then
        assertEquals(EXPENSE_ID_1, readExpenseDto.getExpenseId(), "Read ExpenseDto.id is not equal to: " + EXPENSE_ID_1);
    }

    @Test
    void givenNewExpenseDto_whenNewExpense_thenExpenseDtoNotNull() {
        //Given
        NewExpenseDto newExpenseDto = new NewExpenseDto();
        ExpenseEntity expenseEntity = new ExpenseEntity();
        ExpenseEntity savedExpenseEntity = new ExpenseEntity();
        ExpenseDto expenseDto = new ExpenseDto();

        //When
        when(expenseMapper.fromNewDtoToEntity(newExpenseDto)).thenReturn(expenseEntity);
        when(expenseRepository.save(expenseEntity)).thenReturn(savedExpenseEntity);
        when(expenseMapper.fromEntityToDto(savedExpenseEntity)).thenReturn(expenseDto);

        ExpenseDto savedExpenseDto = expenseService.create(newExpenseDto);

        //Then
        assertNotNull(savedExpenseDto, "Saved ExpenseDto is null");
    }

    @Test
    void given_whenList_thenExpenseDtosSizeEquals() {
        //Given
        List<ExpenseEntity> expenseEntities = List.of(
                new ExpenseEntity(),
                new ExpenseEntity()
        );

        List<ExpenseDto> expenseDtos = List.of(
                new ExpenseDto(),
                new ExpenseDto()
        );

        //When
        when(expenseRepository.findAll()).thenReturn(expenseEntities);
        when(expenseMapper.fromEntitiesToDtos(expenseEntities)).thenReturn(expenseDtos);
        List<ExpenseDto> resultExpenseDtos = expenseService.list();

        //Then
        assertEquals(expenseDtos.size(), resultExpenseDtos.size(), "Result ExpenseDtos.size isn't equal to: " + expenseDtos.size());
    }
}