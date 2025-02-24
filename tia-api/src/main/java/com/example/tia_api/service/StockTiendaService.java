package com.example.tia_api.service;

import com.example.tia_api.model.StockTienda;
import com.example.tia_api.repository.StockTiendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockTiendaService {
    private final StockTiendaRepository stockTiendaRepository;

    public List<StockTienda> getAllStock() {
        return stockTiendaRepository.findAll();
    }

    public Optional<StockTienda> getStockById(Long id) {
        return stockTiendaRepository.findById(id);
    }

    public StockTienda saveStock(StockTienda stockTienda) {
        return stockTiendaRepository.save(stockTienda);
    }

    public Optional<StockTienda> updateStock(Long id, StockTienda stockTienda) {
        if (!stockTiendaRepository.existsById(id)) return Optional.empty();
        stockTienda.setId(id);
        return Optional.of(stockTiendaRepository.save(stockTienda));
    }

    public boolean deleteStock(Long id) {
        if (!stockTiendaRepository.existsById(id)) return false;
        stockTiendaRepository.deleteById(id);
        return true;
    }
}
