package it.sosinski.financecontrol.repository.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static it.sosinski.financecontrol.repository.entity.EntityConstants.TABLE_NAME_EXPENSES;

@Entity
@Table(name = TABLE_NAME_EXPENSES)
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    private Date date;

    private Long categoryId;

    private BigDecimal price;

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ExpenseEntity{" +
                "expenseId=" + expenseId +
                ", date=" + date +
                ", categoryId=" + categoryId +
                ", price=" + price +
                '}';
    }
}
