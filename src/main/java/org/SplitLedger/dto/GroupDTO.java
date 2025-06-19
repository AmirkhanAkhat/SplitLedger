package org.SplitLedger.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private String groupTitle;
    private BigDecimal myShare;
    private boolean paid;
}
