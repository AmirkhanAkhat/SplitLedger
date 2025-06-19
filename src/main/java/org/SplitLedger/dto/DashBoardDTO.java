package org.SplitLedger.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashBoardDTO {
    private String username;
    private String email;
    private BigDecimal totalOwed;
    private BigDecimal totalLent;
    private List<DebtDTO> debts;
    private List<ReminderDTO> reminders;
    private List<GroupDTO> groups;
}
