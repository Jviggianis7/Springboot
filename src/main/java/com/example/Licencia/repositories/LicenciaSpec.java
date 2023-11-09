package com.example.Licencia.repositories;

import org.springframework.data.jpa.domain.Specification;

import com.example.Licencia.models.Funcionario;
import com.example.Licencia.models.LicenciaModel;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class LicenciaSpec {

    public static Specification<LicenciaModel> getLincenFuncio(Long idFuncio){
        return new Specification<LicenciaModel>() {
            @Override
            public Predicate toPredicate(Root<LicenciaModel> root, CriteriaQuery<?> query,
                   CriteriaBuilder criteriaBuilder){
                    return criteriaBuilder.equal(root.get("funcionario").get("id"), idFuncio);

            }
        };
    }

    public static Specification<LicenciaModel> getLicenName(Long idFuncio, String nameLicen){
        return new Specification<LicenciaModel>() {
            @Override
            public Predicate toPredicate(Root<LicenciaModel> root, CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder){

                Predicate prediFuncio = criteriaBuilder.equal(root.get("funcionario").get("id"), idFuncio);
                Predicate prediName = criteriaBuilder.equal(root.get("Nombre"), nameLicen);
                return criteriaBuilder.and(prediFuncio, prediName);
                
            }
        };
    }
    
    public static Specification<LicenciaModel> getLicenStatus(){
        return new Specification<LicenciaModel>() {

            @Override
            public Predicate toPredicate(Root<LicenciaModel> root, CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {
                        return criteriaBuilder.equal(root.get("Estado"), "desactivada");
            }
            
        };
    }
    
}
