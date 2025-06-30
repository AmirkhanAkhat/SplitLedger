package org.SplitLedger.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
public class DebtorListResponse {
    private BigDecimal totalLent;
    private long totalActiveLents;
    private List<DebtorDTO> debtors;
}
