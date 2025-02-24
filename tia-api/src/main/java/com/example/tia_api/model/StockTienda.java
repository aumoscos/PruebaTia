package com.example.tia_api.model;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "stock_tienda")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class StockTienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "tienda_id", nullable = false)
    private Tienda tienda;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    private int stock;
    private int ventas;
}
