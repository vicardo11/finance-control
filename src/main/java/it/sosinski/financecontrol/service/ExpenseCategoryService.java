package it.sosinski.financecontrol.service;

import it.sosinski.financecontrol.core.exception.ExpenseCategoryAlreadyExists;
import it.sosinski.financecontrol.logging.LogInfo;
import it.sosinski.financecontrol.repository.ExpenseCategoryRepository;
import it.sosinski.financecontrol.repository.entity.ExpenseCategory;
import it.sosinski.financecontrol.service.mapper.ExpenseCategoryMapper;
import it.sosinski.financecontrol.web.dto.ExpenseCategoryDto;
import it.sosinski.financecontrol.web.dto.NewExpenseCategoryDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ExpenseCategoryService {

    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final ExpenseCategoryMapper expenseCategoryMapper;

    public ExpenseCategoryService(ExpenseCategoryRepository expenseCategoryRepository, ExpenseCategoryMapper expenseCategoryMapper) {
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.expenseCategoryMapper = expenseCategoryMapper;
    }

    @LogInfo
    public List<ExpenseCategoryDto> list() {

        List<ExpenseCategory> expenseCategories = listExpenseCategories();
        List<ExpenseCategoryDto> expenseCategoryDtos = mapFromExpenseCategoriesToExpenseCategoryDtos(expenseCategories);

        return expenseCategoryDtos;
    }

    @LogInfo
    public ExpenseCategoryDto create(NewExpenseCategoryDto newExpenseCategoryDto) throws ExpenseCategoryAlreadyExists {

        ExpenseCategory expenseCategory = mapFromNewExpenseCategoryDtoToExpense(newExpenseCategoryDto);

        if (categoryNameExists(expenseCategory.getName())) {
            throw new ExpenseCategoryAlreadyExists("ExpenseCategory already exists with name: " + expenseCategory.getName());
        }

        ExpenseCategory savedExpenseCategory = saveExpenseCategory(expenseCategory);
        ExpenseCategoryDto expenseCategoryDto = mapFromExpenseCategoryToExpenseCategoryDto(savedExpenseCategory);

        return expenseCategoryDto;
    }

    private List<ExpenseCategory> listExpenseCategories() {
        return expenseCategoryRepository.findAll();
    }

    private ExpenseCategory saveExpenseCategory(ExpenseCategory expenseCategory) {
        return expenseCategoryRepository.save(expenseCategory);
    }

    private List<ExpenseCategoryDto> mapFromExpenseCategoriesToExpenseCategoryDtos(List<ExpenseCategory> expenseCategories) {
        return expenseCategoryMapper.fromEntitiesToDtos(expenseCategories);
    }

    private ExpenseCategoryDto mapFromExpenseCategoryToExpenseCategoryDto(ExpenseCategory savedExpenseCategory) {
        return expenseCategoryMapper.fromEntityToDto(savedExpenseCategory);
    }

    private ExpenseCategory mapFromNewExpenseCategoryDtoToExpense(NewExpenseCategoryDto newExpenseCategoryDto) {
        return expenseCategoryMapper.fromNewExpenseCategoryDtoToEntity(newExpenseCategoryDto);
    }

    private boolean categoryNameExists(String expenseCategoryName) {
        return expenseCategoryRepository.findByName(expenseCategoryName).isPresent();
    }
}
