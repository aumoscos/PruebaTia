package com.example.tia_api.controller;

import com.example.tia_api.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> getSummary() {
        return ResponseEntity.ok(dashboardService.getDashboardSummary());
    }

    @GetMapping("/stock")
    public ResponseEntity<Map<String, Integer>> getStockLevels() {
        return ResponseEntity.ok(dashboardService.getStockLevels());
    }

    @GetMapping("/ventas")
    public ResponseEntity<Map<String, Integer>> getSalesData() {
        return ResponseEntity.ok(dashboardService.getSalesData());
    }
}
