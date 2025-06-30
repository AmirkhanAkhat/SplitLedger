package org.SplitLedger.controller;

import lombok.RequiredArgsConstructor;
import org.SplitLedger.dto.dashboard.DashBoardDTO;
import org.SplitLedger.dto.dashboard.DebtListResponse;
import org.SplitLedger.dto.dashboard.DebtorListResponse;
import org.SplitLedger.dto.debts.DebtRequest;
import org.SplitLedger.dto.debts.DebtorRequest;
import org.SplitLedger.dto.debts.DResponse;
import org.SplitLedger.service.DashBoardService;
import org.SplitLedger.service.DebtProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final DashBoardService dashBoardService;
    private final DebtProcessingService debtProcessingService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashBoardDTO> getDashBoard(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(dashBoardService.getDashboard(username));
    }


    @GetMapping("/debts")
    public ResponseEntity<DebtListResponse> getDebts(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(dashBoardService.getDebts(username));
    }

    @GetMapping("/debtors")
    public ResponseEntity<DebtorListResponse> getDebtors(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(dashBoardService.getDebtors(username));
    }


    @PostMapping("/createDebtor")
    public ResponseEntity<DResponse> createDebtor(Authentication authentication, @RequestBody DebtorRequest debtorRequest) {
        String username = authentication.getName();
        return ResponseEntity.ok(debtProcessingService.createDebtor(username, debtorRequest));
    }


    @PostMapping("/createDebt")
    public ResponseEntity<DResponse> createDebt(Authentication authentication, @RequestBody DebtRequest debtRequest) {
        String username = authentication.getName();
        return ResponseEntity.ok(debtProcessingService.createDebt(username, debtRequest));
    }

}