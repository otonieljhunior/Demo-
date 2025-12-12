package com.demo.service;

import com.demo.model.Pedido;
import com.demo.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) { this.repository = repository; }

    public List<Pedido> listarTodos() { return repository.findAll(); }
    public List<Pedido> buscarPorDescricao(String q) { return repository.findByDescricaoContainingIgnoreCase(q); }
    public Pedido salvar(Pedido p) { return repository.save(p); }
    public Pedido buscarPorId(Long id) { return repository.findById(id).orElse(null); }
    public void excluir(Long id) { repository.deleteById(id); }
}
