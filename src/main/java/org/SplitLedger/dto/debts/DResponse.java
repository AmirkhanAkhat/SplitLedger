package org.SplitLedger.dto.debts;

import lombok.Data;

@Data
public class DResponse {
    private boolean requested;
    private String error;

    public DResponse(boolean requested){
        this.requested = requested;
    }

    public DResponse(boolean approve, String error){
        this.requested = approve;
        this.error = error;
    }
}
