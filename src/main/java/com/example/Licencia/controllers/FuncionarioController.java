package com.example.Licencia.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Licencia.models.Funcionario;
import com.example.Licencia.models.LicenciaModel;
import com.example.Licencia.repositories.FuncionarioRepository;
import com.example.Licencia.repositories.LicenciaRepository;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private LicenciaRepository licenciaRepository;

    @GetMapping
    public List<Funcionario> getAlllicencias(){
        return funcionarioRepository.findAll();
    }

    @GetMapping("/mostrar/{id}")
    public ResponseEntity<Funcionario> getFuncionarioId(@PathVariable Long id){
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if (funcionario.isPresent()){
            return ResponseEntity.ok(funcionario.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/registrar")
    public Funcionario crearFuncionario(@RequestBody Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id ,@RequestBody Funcionario upFuncionario){
        Optional<Funcionario> existeID = funcionarioRepository.findById(id);
        if (existeID.isPresent()){
            Funcionario funcionario = existeID.get();
            // actualiza los campos de la licencia con los valores de upLicencia
            funcionario.setNombre(upFuncionario.getNombre());
            funcionario.setCargo(upFuncionario.getCargo());
            funcionario.setTelefono(upFuncionario.getTelefono());
            funcionario.setCorreo(upFuncionario.getCorreo());
            funcionario.setOficina(upFuncionario.getOficina());
            funcionario.setLicencias(upFuncionario.getLicencias());

            funcionarioRepository.save(funcionario);
            return ResponseEntity.ok(funcionario);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable long id){
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if (funcionario.isPresent()){
            funcionarioRepository.delete(funcionario.get());
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{funcionarioId}/licencias")
    public ResponseEntity<List<LicenciaModel>> getLicenciasFuncionario(@PathVariable Long funcionarioId) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(funcionarioId);
        if (funcionario.isPresent()) {
            List<LicenciaModel> licencias = licenciaRepository.findByFuncionario(funcionario.get());
            return ResponseEntity.ok(licencias);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
