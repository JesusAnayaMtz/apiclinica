package com.volt.api.repository;

import com.volt.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {


    //metodos en jpa para retornar medicos activos nada mas
    Page<Medico> findByActivoTrue(Pageable paginacion);
}
