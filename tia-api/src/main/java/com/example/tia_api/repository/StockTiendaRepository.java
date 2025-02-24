package com.example.tia_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tia_api.model.StockTienda;

public interface StockTiendaRepository extends JpaRepository<StockTienda, Long> {
}

