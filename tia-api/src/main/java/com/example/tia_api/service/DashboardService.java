package com.example.tia_api.service;

import com.example.tia_api.model.StockTienda;
import com.example.tia_api.repository.StockTiendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final StockTiendaRepository stockTiendaRepository;

    public Map<String, Object> getDashboardSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalProducts", stockTiendaRepository.count());
        summary.put("totalStock", getTotalStock());
        return summary;
    }

    public Map<String, Integer> getStockLevels() {
        List<StockTienda> stockList = stockTiendaRepository.findAll();
        Map<String, Integer> stockMap = new HashMap<>();

        for (StockTienda stock : stockList) {
            stockMap.put(stock.getProducto().getNombre(), stock.getStock());
        }
        return stockMap;
    }

    public Map<String, Integer> getSalesData() {
        List<StockTienda> stockList = stockTiendaRepository.findAll();
        Map<String, Integer> salesMap = new HashMap<>();

        for (StockTienda stock : stockList) {
            salesMap.put(stock.getProducto().getNombre(), stock.getVentas());
        }
        return salesMap;
    }

    private int getTotalStock() {
        return stockTiendaRepository.findAll().stream()
                .mapToInt(StockTienda::getStock)
                .sum();
    }
}
