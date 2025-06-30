package org.SplitLedger.dto.debts;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.SplitLedger.entity.enums.Currency;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DebtorRequest {
    private String borrowerEmail;
    private BigDecimal amount;
    private Currency currency;
    private String comment;
}
