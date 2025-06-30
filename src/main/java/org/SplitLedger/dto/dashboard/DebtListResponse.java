package org.SplitLedger.dto.dashboard;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class DebtListResponse {
    private BigDecimal totalOwed;
    private long totalActiveDebts;
    private List<DebtDTO> debts;
}
