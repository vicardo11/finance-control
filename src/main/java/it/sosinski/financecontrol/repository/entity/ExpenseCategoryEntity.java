package it.sosinski.financecontrol.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

import static it.sosinski.financecontrol.repository.entity.EntityConstants.TABLE_NAME_EXPENSE_CATEGORIES;

@Getter
@Setter
@ToString
@Entity
@Table(name = TABLE_NAME_EXPENSE_CATEGORIES)
public class ExpenseCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "expenseCategoryEntity")
    @ToString.Exclude
    private Set<ExpenseEntity> expenseEntities;

    public void addExpense(ExpenseEntity expenseEntity) {
        expenseEntities.add(expenseEntity);
        expenseEntity.setExpenseCategoryEntity(this);
    }
}

