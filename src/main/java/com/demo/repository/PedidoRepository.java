package com.demo.repository;

import com.demo.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByDescricaoContainingIgnoreCase(String descricao);
}
