package it.sosinski.financecontrol.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static it.sosinski.financecontrol.repository.entity.EntityConstants.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = TABLE_NAME_EXPENSES)
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    private Date date;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = COLUMN_NAME_EXPENSE_CATEGORY_ID, nullable = false)
    private ExpenseCategory expenseCategory;

    @ManyToOne
    @JoinColumn(name = COLUMN_NAME_ACCOUNT_ID, nullable = false)
    private Account account;
}
