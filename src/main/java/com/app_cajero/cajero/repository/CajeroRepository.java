package com.app_cajero.cajero.repository;

import com.app_cajero.cajero.model.Cajero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CajeroRepository extends JpaRepository<Cajero, Long> {
}
