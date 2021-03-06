package it.sosinski.financecontrol.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewExpenseDto {

    private Date date;
    private Long expenseCategoryId;
    private BigDecimal price;
}
