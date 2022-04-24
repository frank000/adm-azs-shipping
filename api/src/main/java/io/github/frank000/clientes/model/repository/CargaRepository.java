package io.github.frank000.clientes.model.repository;

import io.github.frank000.clientes.model.entity.Carga;
import io.github.frank000.clientes.model.entity.Frete;
import io.github.frank000.clientes.rest.dto.FreteParamentrosDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public interface CargaRepository extends JpaRepository<Carga, Integer>, JpaSpecificationExecutor<Frete> {

}
