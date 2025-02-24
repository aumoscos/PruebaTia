package com.example.tia_api.service;

import com.example.tia_api.model.Tienda;
import com.example.tia_api.repository.TiendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TiendaService {
    private final TiendaRepository tiendaRepository;

    public List<Tienda> getAllTiendas() {
        return tiendaRepository.findAll();
    }

    public Optional<Tienda> getTiendaById(Long id) {
        return tiendaRepository.findById(id);
    }

    public Tienda saveTienda(Tienda tienda) {
        return tiendaRepository.save(tienda);
    }

    public Optional<Tienda> updateTienda(Long id, Tienda tienda) {
        if (!tiendaRepository.existsById(id)) return Optional.empty();
        tienda.setId(id);
        return Optional.of(tiendaRepository.save(tienda));
    }

    public boolean deleteTienda(Long id) {
        if (!tiendaRepository.existsById(id)) return false;
        tiendaRepository.deleteById(id);
        return true;
    }
}
