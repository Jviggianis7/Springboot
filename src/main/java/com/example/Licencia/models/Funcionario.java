package com.example.Licencia.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Funcionario")
@JsonSerialize(as = Funcionario.class)
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

    @OneToMany(mappedBy =  "funcionario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(access = Access.READ_WRITE)
    @JsonIgnoreProperties("funcionario")
    private List<LicenciaModel> licencias;


}
