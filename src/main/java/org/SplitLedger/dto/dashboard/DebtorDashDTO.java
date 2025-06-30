package org.SplitLedger.dto.dashboard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.SplitLedger.entity.enums.Status;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebtorDashDTO {
    private Long id;
    private Long borrowerId;
    private BigDecimal amount;
    private Status status;
}
