package com.example.Licencia.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Licencia.models.LicenciaModel;
import com.example.Licencia.repositories.LicenciaRepository;
import com.example.Licencia.repositories.LicenciaSpec;


@RestController
@RequestMapping("/licencia")
public class LicenciaControllers {
    
    @Autowired
    private LicenciaRepository licenciaRepository;

    @GetMapping("/licencias")
    public List<LicenciaModel> getAlllicencias(){
        return licenciaRepository.findAll();
    }

    @GetMapping("/mostrar/{id}")
    public ResponseEntity<LicenciaModel> getLicenciaById(@PathVariable Long id){
        Optional<LicenciaModel> licencia = licenciaRepository.findById(id);
        if(licencia.isPresent()){
            return ResponseEntity.ok(licencia.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/registro")
    public LicenciaModel crearLicencia(@RequestBody LicenciaModel licencia){
        return licenciaRepository.save(licencia);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<LicenciaModel> updateLicencia(@PathVariable Long id, @RequestBody LicenciaModel upLicencia){
        Optional<LicenciaModel> existeLicencia = licenciaRepository.findById(id);
        if (existeLicencia.isPresent()){
            LicenciaModel licencia = existeLicencia.get();
            // actualiza los campos de la licencia con los valores de upLicencia
            licencia.setNombre(upLicencia.getNombre());
            licencia.setEstado(upLicencia.getEstado());
            licencia.setFecha_ini(upLicencia.getFecha_ini());
            licencia.setFecha_fin(upLicencia.getFecha_fin());
            licencia.setProveedor(upLicencia.getProveedor());
            licencia.setEmpresaMatriz(upLicencia.getEmpresaMatriz());
            licenciaRepository.save(licencia);
            return ResponseEntity.ok(licencia);
        }
        else{
            return ResponseEntity.notFound().build();
        
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteLicencia(@PathVariable Long id){
        Optional<LicenciaModel> licencia = licenciaRepository.findById(id);
        if(licencia.isPresent()){
            licenciaRepository.delete(licencia.get());
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/funcionario/{idFuncio}")
    public ResponseEntity<List<LicenciaModel>> getLicenByFunc(@PathVariable Long idFuncio){
        Specification<LicenciaModel> spec = LicenciaSpec.getLincenFuncio(idFuncio);
        List<LicenciaModel> result = licenciaRepository.findAll(spec);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/nameL/{idFuncio}")
    public ResponseEntity<List<LicenciaModel>> getLicenByFuncioName(@PathVariable Long idFuncio, 
    @RequestParam ("nombreLicencia") String nameLicen){
        Specification<LicenciaModel> spec = LicenciaSpec.getLicenName(idFuncio, nameLicen);
        List<LicenciaModel> getLicenName = licenciaRepository.findAll(spec);
        return ResponseEntity.status(HttpStatus.OK).body(getLicenName);
    }

    @GetMapping("/desactivada")
    public ResponseEntity<List<LicenciaModel>> getStatusLicen(){
        Specification<LicenciaModel> spec = LicenciaSpec.getLicenStatus();
        List<LicenciaModel> status = licenciaRepository.findAll(spec);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

}
