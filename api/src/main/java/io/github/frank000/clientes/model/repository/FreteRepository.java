package io.github.frank000.clientes.model.repository;

import io.github.frank000.clientes.model.entity.Cliente;
import io.github.frank000.clientes.model.entity.Frete;
import io.github.frank000.clientes.rest.dto.FreteParamentrosDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.criteria.Predicate;

import java.util.List;

public interface FreteRepository extends JpaRepository<Frete, Integer>, JpaSpecificationExecutor<Frete> {


    default List<Frete> list(FreteParamentrosDTO searchParams) {
        Specification<Frete> spec = (root, query, cb) -> {
            AtomicReference<Predicate> predicateHolder = new AtomicReference<>(cb.conjunction());

            searchParams.getCliente()
                    .ifPresent(nome -> predicateHolder
                            .setPlain(
                                    cb.and(predicateHolder.get(),
                                            cb.like(cb.lower(root.get("cliente").get("nome")), "%" + nome.trim().toLowerCase() + "%"))));

            searchParams.getCarga()
                    .ifPresent(carga -> predicateHolder
                            .setPlain(
                                    cb.and(predicateHolder.get(),
                                            cb.like(cb.lower(root.get("cargas").get("descricao")), "%" + carga.trim().toLowerCase() + "%"))));

            searchParams.getVeiculo()
                    .ifPresent(vei -> predicateHolder
                            .setPlain(cb.and(predicateHolder.get(),
                                    cb.like(cb.lower(root.get("veiculo").get("descricao")), "%" + vei.trim().toLowerCase() + "%"))));

            searchParams.getPlaca()
                    .ifPresent(placa -> predicateHolder
                            .setPlain(cb.and(predicateHolder.get(),
                                    cb.like(cb.lower(root.get("veiculo").get("placa")), "%" + placa.trim().toLowerCase() + "%"))));

            return predicateHolder.get();
        };
        return findAll(spec);
    }

    Object findByCliente(Cliente cliente);
}
