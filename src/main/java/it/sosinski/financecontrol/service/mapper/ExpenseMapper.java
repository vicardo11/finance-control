package it.sosinski.financecontrol.service.mapper;

import it.sosinski.financecontrol.repository.entity.Expense;
import it.sosinski.financecontrol.web.dto.ExpenseDto;
import it.sosinski.financecontrol.web.dto.NewExpenseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpenseMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseMapper.class);
    private static final ModelMapper mapper = new ModelMapper();

    public ExpenseMapper() {
        mapper.addMappings(new PropertyMap<NewExpenseDto, Expense>() {
            @Override
            protected void configure() {
                skip(destination.getExpenseId());
            }
        });
    }

    public Expense fromDtoToEntity(ExpenseDto expenseDto) {
        LOGGER.info("fromDtoToEntity(" + expenseDto + ")");

        Expense expense = mapper.map(expenseDto, Expense.class);

        LOGGER.info("fromDtoToEntity(...) = " + expense);
        return expense;
    }

    public ExpenseDto fromEntityToDto(Expense expense) {
        LOGGER.info("fromEntityToDto(" + expense + ")");

        ExpenseDto expenseDto = mapper.map(expense, ExpenseDto.class);

        LOGGER.info("fromEntityToDto(...) = " + expenseDto);
        return expenseDto;
    }

    public List<Expense> fromDtosToEntities(List<ExpenseDto> expenseDtos) {
        LOGGER.info("fromDtosToEntities(" + expenseDtos + ")");

        List<Expense> expenseEntities =
                expenseDtos.stream().map(this::fromDtoToEntity).collect(Collectors.toList());

        LOGGER.info("fromDtosToEntities(...) = " + expenseEntities);
        return expenseEntities;
    }

    public List<ExpenseDto> fromEntitiesToDtos(List<Expense> expenseEntities) {
        LOGGER.info("fromEntitiesToDtos(" + expenseEntities + ")");

        List<ExpenseDto> expenseDtos = expenseEntities.stream().map(this::fromEntityToDto).collect(Collectors.toList());

        LOGGER.info("fromEntitiesToDtos(...) = " + expenseDtos);
        return expenseDtos;
    }

    public Expense fromNewDtoToEntity(NewExpenseDto newExpenseDto) {
        LOGGER.info("fromNewDtoToEntity(" + newExpenseDto + ")");

        Expense expense = mapper.map(newExpenseDto, Expense.class);

        LOGGER.info("fromNewDtoToEntity(...) = " + expense);
        return expense;
    }

}
