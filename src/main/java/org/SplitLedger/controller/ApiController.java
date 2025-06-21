package org.SplitLedger.controller;

import lombok.RequiredArgsConstructor;
import org.SplitLedger.config.JwtUtil;
import org.SplitLedger.dto.DashBoardDTO;
import org.SplitLedger.service.DashBoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final JwtUtil jwtUtil;
    private final DashBoardService dashBoardService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashBoardDTO> getDashBoard(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        return ResponseEntity.ok(dashBoardService.getDashboard(username));
    }

}