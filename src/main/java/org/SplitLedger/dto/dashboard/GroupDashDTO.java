package org.SplitLedger.dto.dashboard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDashDTO {
    private String groupTitle;
    private BigDecimal myShare;
    private boolean paid;
}
