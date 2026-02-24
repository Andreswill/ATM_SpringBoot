package com.app_cajero.cajero.repository;

import com.app_cajero.cajero.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


/**
 * Repositorio JPA para la entidad Cliente
 * Permite operaciones CRUD en la base de datos
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByDocumento(String documento);
}
