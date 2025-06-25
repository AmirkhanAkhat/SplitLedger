package org.SplitLedger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
public class DebtorResponse {
    private BigDecimal totalLent;
    private long totalActiveLents;
    private List<DebtorDTO> debtors;
}
