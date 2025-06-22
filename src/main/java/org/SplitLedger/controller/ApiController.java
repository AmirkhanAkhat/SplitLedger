package org.SplitLedger.controller;

import lombok.RequiredArgsConstructor;
import org.SplitLedger.dto.DashBoardDTO;
import org.SplitLedger.service.DashBoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


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

}