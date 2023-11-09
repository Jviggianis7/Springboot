package com.example.Licencia.repositories;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.example.Licencia.models.Funcionario;
import com.example.Licencia.models.LicenciaModel;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class FuncionarioSpec {
    
    public static Specification<Funcionario> funcionarioByServer(){
        return new Specification<Funcionario>(){

            @Override
            public Predicate toPredicate(Root<Funcionario> root, CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {
                        Join<Funcionario, LicenciaModel> join = root.join("licencias", JoinType.INNER);
                        Expression<Long> countExpression = criteriaBuilder.count(join);
                        Expression<Long> oneExpression = criteriaBuilder.literal(2l);
                        
                        Predicate greterThanOne = criteriaBuilder.greaterThan(
                        countExpression,
                        oneExpression);
                        query.groupBy(root.get("id"));
                        query.having(greterThanOne);
                        return null;
            }
        };
    }
}
