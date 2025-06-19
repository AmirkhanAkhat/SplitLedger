package org.SplitLedger.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.SplitLedger.entity.enums.Status;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebtDTO {
    private Long id;
    private Long lenderId;
    private BigDecimal amount;
    private Status status;
}
