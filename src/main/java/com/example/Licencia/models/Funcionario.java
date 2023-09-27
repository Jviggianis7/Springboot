package com.example.Licencia.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY)
    @Column(
        unique = true, nullable = false)
    private Long id;
    private String nombre;
    private String cargo;
    private String telefono;
    private String correo;
    private String oficina;
}
