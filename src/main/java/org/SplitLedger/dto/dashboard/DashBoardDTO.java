package org.SplitLedger.dto.dashboard;
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
    private List<DebtDashDTO> debts;
    private List<DebtorDashDTO> debtors;
    private List<ReminderDashDTO> reminders;
    private List<GroupDashDTO> groups;
}
