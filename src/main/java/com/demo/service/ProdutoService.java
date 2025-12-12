package com.demo.service;

import com.demo.model.Produto;
import com.demo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) { this.repository = repository; }

    public List<Produto> listarTodos() { return repository.findAll(); }
    public List<Produto> buscarPorNome(String q) { return repository.findByNomeContainingIgnoreCase(q); }
    public Produto salvar(Produto p) { return repository.save(p); }
    public Produto buscarPorId(Long id) { return repository.findById(id).orElse(null); }
    public void excluir(Long id) { repository.deleteById(id); }
}
