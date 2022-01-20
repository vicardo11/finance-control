package it.sosinski.financecontrol.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static it.sosinski.financecontrol.repository.entity.EntityConstants.TABLE_NAME_EXPENSES;

@Getter
@Setter
@ToString
@Entity
@Table(name = TABLE_NAME_EXPENSES)
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    private Date date;

    private Long categoryId;

    private BigDecimal price;

    @ManyToOne
    private ExpenseCategoryEntity expenseCategoryEntity;

}
