package org.SplitLedger.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.SplitLedger.entity.enums.Currency;
import org.SplitLedger.entity.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DebtDTO {
    private Long debtId;
    private String lenderUsername;
    private BigDecimal amount;
    private Currency currency;
    private Status status;
    private LocalDateTime createdAt;
    private String description;
}
