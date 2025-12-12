package com.demo.controller;

import com.demo.model.Pedido;
import com.demo.model.Produto;
import com.demo.service.ClienteService;
import com.demo.service.PedidoService;
import com.demo.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoController(PedidoService service, ClienteService clienteService, ProdutoService produtoService) {
        this.service = service;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String q, Model model) {
        if (q != null && !q.isBlank()) {
            model.addAttribute("pedidos", service.buscarPorDescricao(q));
            model.addAttribute("q", q);
        } else {
            model.addAttribute("pedidos", service.listarTodos());
        }
        return "pedido/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("produtos", produtoService.listarTodos());
        return "pedido/form";
    }

    @PostMapping
    public String salvar(
            @ModelAttribute Pedido pedido,
            @RequestParam Long clienteId,
            @RequestParam(required = false) List<Long> produtoIds
    ) {
        // associa cliente
        pedido.setCliente(clienteService.buscarPorId(clienteId));

        // associa produtos (se houver)
        if (produtoIds != null) {
            Set<Produto> produtos = produtoIds.stream()
                    .map(produtoService::buscarPorId)
                    .filter(p -> p != null)
                    .collect(Collectors.toSet());
            pedido.setProdutos(produtos);
        } else {
            pedido.setProdutos(new HashSet<>());
        }

        service.salvar(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Pedido p = service.buscarPorId(id);
        model.addAttribute("pedido", p);
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("produtos", produtoService.listarTodos());
        return "pedido/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/pedidos";
    }
}
