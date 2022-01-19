package it.sosinski.financecontrol.service.mapper;

import it.sosinski.financecontrol.repository.entity.ExpenseEntity;
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
    void givenExpenseEntity_whenMap_thenExpenseDtoIdEquals() {
        //Given
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setExpenseId(EXPENSE_ID_1);

        //When
        ExpenseDto expenseDto = mapper.fromEntityToDto(expenseEntity);

        //Then
        assertEquals(EXPENSE_ID_1, expenseDto.getExpenseId(), "ExpenseDto.id isn't equal to: " + EXPENSE_ID_1);
    }

    @Test
    void givenExpenseEntity_whenMap_thenExpenseDtoCategoryIdEquals() {
        //Given
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setCategoryId(CATEGORY_ID_1);

        //When
        ExpenseDto expenseDto = mapper.fromEntityToDto(expenseEntity);

        //Then
        assertEquals(CATEGORY_ID_1, expenseDto.getCategoryId(), "ExpenseDto.categoryId isn't equal to: " + CATEGORY_ID_1);
    }

    @Test
    void givenExpenseEntity_whenMap_thenExpenseDtoDateEquals() {
        //Given
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setDate(EXPENSE_DATE);

        //When
        ExpenseDto expenseDto = mapper.fromEntityToDto(expenseEntity);

        //Then
        assertEquals(EXPENSE_DATE, expenseDto.getDate(), "ExpenseDto.date isn't equal to: " + EXPENSE_DATE);
    }

    @Test
    void givenExpenseEntity_whenMap_thenExpenseDtoPriceEquals() {
        //Given
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setPrice(EXPENSE_PRICE_10);

        //When
        ExpenseDto expenseDto = mapper.fromEntityToDto(expenseEntity);

        //Then
        assertEquals(EXPENSE_PRICE_10, expenseDto.getPrice(), "ExpenseDto.price isn't equal to: " + EXPENSE_PRICE_10);
    }

    @Test
    void givenExpenseDto_whenMap_thenExpenseEntityIdEquals() {
        //Given
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setExpenseId(EXPENSE_ID_1);

        //When
        ExpenseEntity expenseEntity = mapper.fromDtoToEntity(expenseDto);

        //Then
        assertEquals(EXPENSE_ID_1, expenseEntity.getExpenseId(), "ExpenseEntity.id isn't equal to: " + EXPENSE_ID_1);
    }

    @Test
    void givenExpenseDto_whenMap_thenExpenseEntityCategoryIdEquals() {
        //Given
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setCategoryId(CATEGORY_ID_1);

        //When
        ExpenseEntity expenseEntity = mapper.fromDtoToEntity(expenseDto);

        //Then
        assertEquals(CATEGORY_ID_1, expenseEntity.getCategoryId(), "ExpenseEntity.categoryId isn't equal to: " + CATEGORY_ID_1);
    }

    @Test
    void givenExpenseDto_whenMap_thenExpenseEntityDateEquals() {
        //Given
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setDate(EXPENSE_DATE);

        //When
        ExpenseEntity expenseEntity = mapper.fromDtoToEntity(expenseDto);

        //Then
        assertEquals(EXPENSE_DATE, expenseEntity.getDate(), "ExpenseEntity.date isn't equal to: " + EXPENSE_DATE);
    }

    @Test
    void givenExpenseDto_whenMap_thenExpenseEntityPriceEquals() {
        //Given
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setPrice(EXPENSE_PRICE_10);

        //When
        ExpenseEntity expenseEntity = mapper.fromDtoToEntity(expenseDto);

        //Then
        assertEquals(EXPENSE_PRICE_10, expenseEntity.getPrice(), "ExpenseEntity.price isn't equal to: " + EXPENSE_PRICE_10);
    }

    @Test
    void givenNewExpenseDto_whenMap_thenExpenseEntityCategoryIdEquals() {
        //Given
        NewExpenseDto newExpenseDto = new NewExpenseDto();
        newExpenseDto.setCategoryId(CATEGORY_ID_1);

        //When
        ExpenseEntity expenseEntity = mapper.fromNewDtoToEntity(newExpenseDto);

        //Then
        assertEquals(CATEGORY_ID_1, expenseEntity.getCategoryId(), "ExpenseEntity.price isn't equal to: " + CATEGORY_ID_1);
    }

    @Test
    void givenNewExpenseDto_whenMap_thenExpenseEntityDateEquals() {
        //Given
        NewExpenseDto newExpenseDto = new NewExpenseDto();
        newExpenseDto.setDate(EXPENSE_DATE);

        //When
        ExpenseEntity expenseEntity = mapper.fromNewDtoToEntity(newExpenseDto);

        //Then
        assertEquals(EXPENSE_DATE, expenseEntity.getDate(), "ExpenseEntity.date isn't equal to: " + EXPENSE_DATE);
    }

    @Test
    void givenNewExpenseDto_whenMap_thenExpenseEntityPriceEquals() {
        //Given
        NewExpenseDto newExpenseDto = new NewExpenseDto();
        newExpenseDto.setPrice(EXPENSE_PRICE_10);

        //When
        ExpenseEntity expenseEntity = mapper.fromNewDtoToEntity(newExpenseDto);

        //Then
        assertEquals(EXPENSE_PRICE_10, expenseEntity.getPrice(), "ExpenseEntity.price isn't equal to: " + EXPENSE_PRICE_10);
    }
}