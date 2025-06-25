package org.SplitLedger.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class DebtResponse {
    private BigDecimal totalOwed;
    private long totalActiveDebts;
    private List<DebtDTO> debts;
}
