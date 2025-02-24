package com.example.tia_api.service;

import com.example.tia_api.model.Producto;
import com.example.tia_api.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> updateProducto(Long id, Producto producto) {
        if (!productoRepository.existsById(id)) return Optional.empty();
        producto.setId(id);
        return Optional.of(productoRepository.save(producto));
    }

    public boolean deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) return false;
        productoRepository.deleteById(id);
        return true;
    }
}
