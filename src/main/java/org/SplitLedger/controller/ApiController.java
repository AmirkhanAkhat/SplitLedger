package org.SplitLedger.controller;

import lombok.RequiredArgsConstructor;
import org.SplitLedger.dto.*;
import org.SplitLedger.service.DashBoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final DashBoardService dashBoardService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashBoardDTO> getDashBoard(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(dashBoardService.getDashboard(username));
    }


    @GetMapping("/debts")
    public ResponseEntity<DebtResponse> getDebts(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(dashBoardService.getDebts(username));
    }

    @GetMapping("/debtors")
    public ResponseEntity<DebtorResponse> getDebtors(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(dashBoardService.getDebtors(username));
    }

}