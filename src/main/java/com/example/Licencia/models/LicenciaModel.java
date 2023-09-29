package com.example.Licencia.models;

import java.sql.Date;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
    
}
