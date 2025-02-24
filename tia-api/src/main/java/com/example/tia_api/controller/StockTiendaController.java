package com.example.tia_api.controller;

import com.example.tia_api.model.StockTienda;
import com.example.tia_api.service.StockTiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockTiendaController {
    private final StockTiendaService stockTiendaService;

    @GetMapping
    public List<StockTienda> getAllStock() {
        return stockTiendaService.getAllStock();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockTienda> getStockById(@PathVariable Long id) {
        return stockTiendaService.getStockById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StockTienda createStock(@RequestBody StockTienda stockTienda) {
        return stockTiendaService.saveStock(stockTienda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockTienda> updateStock(@PathVariable Long id, @RequestBody StockTienda stockTienda) {
        Optional<StockTienda> updatedStock = stockTiendaService.updateStock(id, stockTienda);
        return updatedStock.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        if (!stockTiendaService.deleteStock(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
