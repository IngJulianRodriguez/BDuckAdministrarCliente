package com.bankduck.AdministrarCliente.repository;

import com.bankduck.AdministrarCliente.entities.ServiciosCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiciosClienteRepository extends JpaRepository<ServiciosCliente, Long> {
    Optional<ServiciosCliente> findByClienteCedulaAndServicioId(Long cliente_id, Long servicio_id);
    Optional<ServiciosCliente> findByClienteCedulaAndEstado(Long clienteCedula, String estado);


}

