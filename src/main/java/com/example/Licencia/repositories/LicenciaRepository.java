package com.example.Licencia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Licencia.models.Funcionario;
import com.example.Licencia.models.LicenciaModel;

@Repository
public interface LicenciaRepository extends JpaRepository<LicenciaModel, Long> {
      List<LicenciaModel> findByFuncionario(Funcionario funcionario);
    
}
