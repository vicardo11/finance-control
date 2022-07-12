package it.sosinski.financecontrol.service.mapper;

import it.sosinski.financecontrol.logging.LogInfo;
import it.sosinski.financecontrol.repository.entity.Expense;
import it.sosinski.financecontrol.web.dto.ExpenseDto;
import it.sosinski.financecontrol.web.dto.NewExpenseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpenseMapper {

    private static final ModelMapper mapper = new ModelMapper();

    @LogInfo
    public Expense fromDtoToEntity(ExpenseDto expenseDto) {

        return mapper.map(expenseDto, Expense.class);
    }

    @LogInfo
    public ExpenseDto fromEntityToDto(Expense expense) {

        return mapper.map(expense, ExpenseDto.class);
    }

    @LogInfo
    public List<Expense> fromDtosToEntities(List<ExpenseDto> expenseDtos) {

        return expenseDtos.stream().map(this::fromDtoToEntity).collect(Collectors.toList());
    }

    @LogInfo
    public List<ExpenseDto> fromEntitiesToDtos(List<Expense> expenseEntities) {

        return expenseEntities.stream().map(this::fromEntityToDto).collect(Collectors.toList());
    }

    @LogInfo
    public Expense fromNewDtoToEntity(NewExpenseDto newExpenseDto) {

        return mapper.map(newExpenseDto, Expense.class);
    }

}
