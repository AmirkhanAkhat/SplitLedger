package org.SplitLedger.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.SplitLedger.entity.enums.Status;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReminderDTO {
    private String message;
    private Status status;
}
