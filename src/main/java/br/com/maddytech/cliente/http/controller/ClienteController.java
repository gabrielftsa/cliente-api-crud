package br.com.maddytech.cliente.http.controller;

import br.com.maddytech.cliente.entity.Cliente;
import br.com.maddytech.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.badRequest;


@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente salvar(@RequestBody Cliente cliente){
            return clienteService.salvarCliente(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarCliente(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(clienteService.buscarPorId(id));
        } catch (RuntimeException e) {
            return badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCliente(@PathVariable Long id, @RequestBody Cliente email){
        try {
            clienteService.atualizarEmail(id, email);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removerCliente(@PathVariable Long id) {
        try {
            clienteService.removerCliente(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return badRequest().body(e.getMessage());
        }
    }
}