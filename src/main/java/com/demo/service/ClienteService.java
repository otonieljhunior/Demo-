package com.demo.service;

import com.demo.model.Cliente;
import com.demo.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) { this.repository = repository; }

    public List<Cliente> listarTodos() { return repository.findAll(); }
    public List<Cliente> buscarPorNome(String q) { return repository.findByNomeContainingIgnoreCase(q); }
    public Cliente salvar(Cliente c) { return repository.save(c); }
    public Cliente buscarPorId(Long id) { return repository.findById(id).orElse(null); }
    public void excluir(Long id) { repository.deleteById(id); }
}
