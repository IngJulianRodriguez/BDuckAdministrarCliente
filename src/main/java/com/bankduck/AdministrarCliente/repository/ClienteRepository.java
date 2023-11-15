package com.bankduck.AdministrarCliente.repository;

import com.bankduck.AdministrarCliente.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCedula(Long cedula);
}
