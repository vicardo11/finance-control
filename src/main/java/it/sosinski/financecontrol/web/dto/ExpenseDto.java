package it.sosinski.financecontrol.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ExpenseDto {

    private Long expenseId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Long categoryId;
    private BigDecimal price;
}
