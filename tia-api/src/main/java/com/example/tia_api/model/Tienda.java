package com.example.tia_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tiendas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor

public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private String ubicacion;
}
