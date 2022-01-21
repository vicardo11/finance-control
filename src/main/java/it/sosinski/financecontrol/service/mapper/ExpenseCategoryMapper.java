package it.sosinski.financecontrol.service.mapper;

import it.sosinski.financecontrol.repository.entity.ExpenseCategoryEntity;
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

    public ExpenseCategoryDto fromEntityToDto(ExpenseCategoryEntity expenseCategoryEntity) {
        LOGGER.info("fromEntityToDto(" + expenseCategoryEntity + ")");

        ExpenseCategoryDto expenseCategoryDto = mapper.map(expenseCategoryEntity, ExpenseCategoryDto.class);

        LOGGER.info("fromEntityToDto(...) = " + expenseCategoryDto);
        return expenseCategoryDto;
    }

    public ExpenseCategoryEntity fromNewExpenseCategoryDtoToEntity(NewExpenseCategoryDto NewExpenseCategoryDto) {
        LOGGER.info("fromNewExpenseCategoryDtoToEntity(" + NewExpenseCategoryDto + ")");

        ExpenseCategoryEntity expenseCategoryEntity = mapper.map(NewExpenseCategoryDto, ExpenseCategoryEntity.class);

        LOGGER.info("fromNewExpenseCategoryDtoToEntity(...) = " + expenseCategoryEntity);
        return expenseCategoryEntity;
    }
}
