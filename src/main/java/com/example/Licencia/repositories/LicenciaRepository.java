package com.example.Licencia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Licencia.models.LicenciaModel;

@Repository
public interface LicenciaRepository extends JpaRepository<LicenciaModel, Long> {
    
    
}
