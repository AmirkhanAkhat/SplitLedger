package org.SplitLedger.dto.dashboard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.SplitLedger.entity.enums.ReminderStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReminderDashDTO {
    private String message;
    private ReminderStatus status;
}
