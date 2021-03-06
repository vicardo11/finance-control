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
public class ExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseCategoryId;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "expenseCategory")
    @ToString.Exclude
    private Set<Expense> expenses;

    public void addExpense(Expense expense) {
        expenses.add(expense);
        expense.setExpenseCategory(this);
    }

    public void deleteExpense(Expense expense) {
        expenses.remove(expense);
    }
}

