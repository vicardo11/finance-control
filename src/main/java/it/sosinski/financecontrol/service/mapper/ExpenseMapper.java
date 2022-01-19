package it.sosinski.financecontrol.service.mapper;

import it.sosinski.financecontrol.repository.entity.ExpenseEntity;
import it.sosinski.financecontrol.web.dto.ExpenseDto;
import it.sosinski.financecontrol.web.dto.NewExpenseDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpenseMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseMapper.class);
    private static final ModelMapper mapper = new ModelMapper();

    public ExpenseEntity fromDtoToEntity(ExpenseDto expenseDto) {
        LOGGER.info("from(" + expenseDto + ")");

        ExpenseEntity expenseEntity = mapper.map(expenseDto, ExpenseEntity.class);

        LOGGER.info("from(...) = " + expenseEntity);
        return expenseEntity;
    }

    public ExpenseDto fromEntityToDto(ExpenseEntity expenseEntity) {
        LOGGER.info("from(" + expenseEntity + ")");

        ExpenseDto expenseDto = mapper.map(expenseEntity, ExpenseDto.class);

        LOGGER.info("from(...) = " + expenseEntity);
        return expenseDto;
    }

    public List<ExpenseEntity> fromDtosToEntities(List<ExpenseDto> expenseDtos) {
        LOGGER.info("fromDtos(" + expenseDtos + ")");

        List<ExpenseEntity> expenseEntities =
                expenseDtos.stream().map(this::fromDtoToEntity).collect(Collectors.toList());

        LOGGER.info("fromDtos(...) = " + expenseEntities);
        return expenseEntities;
    }

    public List<ExpenseDto> fromEntitiesToDtos(List<ExpenseEntity> expenseEntities) {
        LOGGER.info("fromEntities(" + expenseEntities + ")");

        List<ExpenseDto> expenseDtos = expenseEntities.stream().map(this::fromEntityToDto).collect(Collectors.toList());

        LOGGER.info("fromEntities(...) = " + expenseDtos);
        return expenseDtos;
    }

    public ExpenseEntity fromNewDtoToEntity(NewExpenseDto newExpenseDto) {
        LOGGER.info("fromNewDtoToEntity(" + newExpenseDto + ")");

        ExpenseEntity expenseEntity = mapper.map(newExpenseDto, ExpenseEntity.class);

        LOGGER.info("fromNewDtoToEntity(...) = " + expenseEntity);
        return expenseEntity;
    }

}