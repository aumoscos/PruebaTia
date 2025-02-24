package com.example.tia_api.controller;

import com.example.tia_api.model.Tienda;
import com.example.tia_api.service.TiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiendas")
@RequiredArgsConstructor
public class TiendaController {
    private final TiendaService tiendaService;

    @GetMapping
    public ResponseEntity<List<Tienda>> getAllTiendas() {
        return ResponseEntity.ok(tiendaService.getAllTiendas());
    }

    @PostMapping
    public ResponseEntity<Tienda> createTienda(@RequestBody Tienda tienda) {
        return ResponseEntity.ok(tiendaService.saveTienda(tienda));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tienda> getTiendaById(@PathVariable Long id) {
        return tiendaService.getTiendaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tienda> updateTienda(@PathVariable Long id, @RequestBody Tienda tienda) {
        return tiendaService.updateTienda(id, tienda)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTienda(@PathVariable Long id) {
        return tiendaService.deleteTienda(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
