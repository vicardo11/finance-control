package it.sosinski.financecontrol.service.mapper;

import it.sosinski.financecontrol.logging.LogInfo;
import it.sosinski.financecontrol.repository.entity.ExpenseCategory;
import it.sosinski.financecontrol.web.dto.ExpenseCategoryDto;
import it.sosinski.financecontrol.web.dto.NewExpenseCategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpenseCategoryMapper {

    private static final ModelMapper mapper = new ModelMapper();

    @LogInfo
    public ExpenseCategoryDto fromEntityToDto(ExpenseCategory expenseCategory) {

        return mapper.map(expenseCategory, ExpenseCategoryDto.class);
    }

    @LogInfo
    public ExpenseCategory fromNewExpenseCategoryDtoToEntity(NewExpenseCategoryDto NewExpenseCategoryDto) {

        return mapper.map(NewExpenseCategoryDto, ExpenseCategory.class);
    }

    @LogInfo
    public List<ExpenseCategoryDto> fromEntitiesToDtos(List<ExpenseCategory> expenseCategories) {

        return expenseCategories.stream().map(this::fromEntityToDto).collect(Collectors.toList());
    }
}
