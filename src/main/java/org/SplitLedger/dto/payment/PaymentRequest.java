package org.SplitLedger.dto.payment;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.SplitLedger.entity.enums.Method;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentRequest {
    private Long debtId;
    private BigDecimal amount;
    private Method method;
    private String comment;
}
