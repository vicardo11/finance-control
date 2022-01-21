package it.sosinski.financecontrol.service.mapper;

import it.sosinski.financecontrol.repository.entity.ExpenseCategory;
import it.sosinski.financecontrol.repository.entity.Expense;
import it.sosinski.financecontrol.web.dto.ExpenseDto;
import it.sosinski.financecontrol.web.dto.NewExpenseDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseMapperTest {

    public static final long CATEGORY_ID_1 = 1L;
    public static final Date EXPENSE_DATE = Date.valueOf("1980-01-12");
    public static final BigDecimal EXPENSE_PRICE_10 = BigDecimal.valueOf(10.0);
    ExpenseMapper mapper = new ExpenseMapper();

    public static final long EXPENSE_ID_1 = 1L;

    @Test
    void givenExpense_whenMap_thenExpenseDtoIdEquals() {
        //Given
        Expense expense = new Expense();
        expense.setExpenseId(EXPENSE_ID_1);

        //When
        ExpenseDto expenseDto = mapper.fromEntityToDto(expense);

        //Then
        assertEquals(EXPENSE_ID_1, expenseDto.getExpenseId(), "ExpenseDto.id isn't equal to: " + EXPENSE_ID_1);
    }

    @Test
    void givenExpense_whenMap_thenExpenseDtoCategoryIdEquals() {
        //Given
        Expense expense = new Expense();
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setId(CATEGORY_ID_1);
        expense.setExpenseCategory(expenseCategory);

        //When
        ExpenseDto expenseDto = mapper.fromEntityToDto(expense);

        //Then
        assertEquals(CATEGORY_ID_1, expenseDto.getExpenseCategoryId(), "ExpenseDto.categoryId isn't equal to: " + CATEGORY_ID_1);
    }

    @Test
    void givenExpense_whenMap_thenExpenseDtoDateEquals() {
        //Given
        Expense expense = new Expense();
        expense.setDate(EXPENSE_DATE);

        //When
        ExpenseDto expenseDto = mapper.fromEntityToDto(expense);

        //Then
        assertEquals(EXPENSE_DATE, expenseDto.getDate(), "ExpenseDto.date isn't equal to: " + EXPENSE_DATE);
    }

    @Test
    void givenExpense_whenMap_thenExpenseDtoPriceEquals() {
        //Given
        Expense expense = new Expense();
        expense.setPrice(EXPENSE_PRICE_10);

        //When
        ExpenseDto expenseDto = mapper.fromEntityToDto(expense);

        //Then
        assertEquals(EXPENSE_PRICE_10, expenseDto.getPrice(), "ExpenseDto.price isn't equal to: " + EXPENSE_PRICE_10);
    }

    @Test
    void givenExpenseDto_whenMap_thenExpenseIdEquals() {
        //Given
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setExpenseId(EXPENSE_ID_1);

        //When
        Expense expense = mapper.fromDtoToEntity(expenseDto);

        //Then
        assertEquals(EXPENSE_ID_1, expense.getExpenseId(), "Expense.id isn't equal to: " + EXPENSE_ID_1);
    }

    @Test
    void givenExpenseDto_whenMap_thenExpenseDateEquals() {
        //Given
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setDate(EXPENSE_DATE);

        //When
        Expense expense = mapper.fromDtoToEntity(expenseDto);

        //Then
        assertEquals(EXPENSE_DATE, expense.getDate(), "Expense.date isn't equal to: " + EXPENSE_DATE);
    }

    @Test
    void givenExpenseDto_whenMap_thenExpensePriceEquals() {
        //Given
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setPrice(EXPENSE_PRICE_10);

        //When
        Expense expense = mapper.fromDtoToEntity(expenseDto);

        //Then
        assertEquals(EXPENSE_PRICE_10, expense.getPrice(), "Expense.price isn't equal to: " + EXPENSE_PRICE_10);
    }

    @Test
    void givenNewExpenseDto_whenMap_thenExpenseDateEquals() {
        //Given
        NewExpenseDto newExpenseDto = new NewExpenseDto();
        newExpenseDto.setDate(EXPENSE_DATE);

        //When
        Expense expense = mapper.fromNewDtoToEntity(newExpenseDto);

        //Then
        assertEquals(EXPENSE_DATE, expense.getDate(), "Expense.date isn't equal to: " + EXPENSE_DATE);
    }

    @Test
    void givenNewExpenseDto_whenMap_thenExpensePriceEquals() {
        //Given
        NewExpenseDto newExpenseDto = new NewExpenseDto();
        newExpenseDto.setPrice(EXPENSE_PRICE_10);

        //When
        Expense expense = mapper.fromNewDtoToEntity(newExpenseDto);

        //Then
        assertEquals(EXPENSE_PRICE_10, expense.getPrice(), "Expense.price isn't equal to: " + EXPENSE_PRICE_10);
    }
}