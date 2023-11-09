package com.example.Licencia.models;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Licencia")
public class LicenciaModel {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY)
    @Column(
        unique = true, nullable = false)
    private Long id;
    private String Nombre;
    private String Estado;
    private Date Fecha_ini;
    private Date Fecha_fin;
    private String Proveedor;
    private String EmpresaMatriz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario")
    @JsonIgnoreProperties("licencias")
    private Funcionario funcionario;
    
}
