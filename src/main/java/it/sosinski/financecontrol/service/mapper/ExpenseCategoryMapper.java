package it.sosinski.financecontrol.service.mapper;

import it.sosinski.financecontrol.repository.entity.ExpenseCategory;
import it.sosinski.financecontrol.web.dto.ExpenseCategoryDto;
import it.sosinski.financecontrol.web.dto.NewExpenseCategoryDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExpenseCategoryMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseCategoryMapper.class);
    private static final ModelMapper mapper = new ModelMapper();

    public ExpenseCategoryDto fromEntityToDto(ExpenseCategory expenseCategory) {
        LOGGER.info("fromEntityToDto(" + expenseCategory + ")");

        ExpenseCategoryDto expenseCategoryDto = mapper.map(expenseCategory, ExpenseCategoryDto.class);

        LOGGER.info("fromEntityToDto(...) = " + expenseCategoryDto);
        return expenseCategoryDto;
    }

    public ExpenseCategory fromNewExpenseCategoryDtoToEntity(NewExpenseCategoryDto NewExpenseCategoryDto) {
        LOGGER.info("fromNewExpenseCategoryDtoToEntity(" + NewExpenseCategoryDto + ")");

        ExpenseCategory expenseCategory = mapper.map(NewExpenseCategoryDto, ExpenseCategory.class);

        LOGGER.info("fromNewExpenseCategoryDtoToEntity(...) = " + expenseCategory);
        return expenseCategory;
    }
}
