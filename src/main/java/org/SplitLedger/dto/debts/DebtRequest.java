package org.SplitLedger.dto.debts;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.SplitLedger.entity.enums.Currency;


import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DebtRequest {
    private String lenderEmail;
    private BigDecimal amount;
    private Currency currency;
    private String comment;
}
