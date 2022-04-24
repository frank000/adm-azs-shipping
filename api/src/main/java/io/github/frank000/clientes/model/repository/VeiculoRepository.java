package io.github.frank000.clientes.model.repository;

import io.github.frank000.clientes.model.entity.Frete;
import io.github.frank000.clientes.model.entity.Veiculo;
import io.github.frank000.clientes.rest.dto.FreteParamentrosDTO;
import io.github.frank000.clientes.rest.dto.VeiculoParamentrosDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>, JpaSpecificationExecutor<Veiculo> {

    default List<Veiculo> list(VeiculoParamentrosDTO searchParams) {
        Specification<Veiculo> spec = (root, query, cb) -> {
            AtomicReference<Predicate> predicateHolder = new AtomicReference<>(cb.conjunction());

            if(searchParams != null){
                searchParams.getCarga()
                        .ifPresent(carga -> predicateHolder
                                .setPlain(
                                        cb.and(predicateHolder.get(),
                                                cb.like(cb.lower(root.get("capacidade")), "%" + carga.trim().toLowerCase() + "%"))));

                searchParams.getVeiculo()
                        .ifPresent(vei -> predicateHolder
                                .setPlain(cb.and(predicateHolder.get(),
                                        cb.like(cb.lower(root.get("descricao")), "%" + vei.trim().toLowerCase() + "%"))));

                searchParams.getPlaca()
                        .ifPresent(placa -> predicateHolder
                                .setPlain(cb.and(predicateHolder.get(),
                                        cb.like(cb.lower(root.get("placa")), "%" + placa.trim().toLowerCase() + "%"))));
            }


            return predicateHolder.get();
        };
        return findAll(spec);
    }
}
