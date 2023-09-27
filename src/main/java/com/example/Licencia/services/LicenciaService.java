package com.example.Licencia.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Licencia.models.LicenciaModel;
import com.example.Licencia.repositories.LicenciaRepository;

@Service
public class LicenciaService {
    @Autowired
    LicenciaRepository licenciaRepository; 

    public ArrayList<LicenciaModel> obtenerLicencias(){
        return (ArrayList<LicenciaModel>) licenciaRepository.findAll();
    }
}
