package br.com.maddytech.cliente.service;

import br.com.maddytech.cliente.entity.Cliente;
import br.com.maddytech.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvarCliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarCliente(@PathVariable Long id){
        buscarPorId(id);
        return clienteRepository.findById(id);
    }

    public void atualizarEmail(@PathVariable Long id, @RequestBody Cliente email){
        var atualizacao = buscarPorId(id);
        atualizacao.setEmail(email.getEmail());
        clienteRepository.save(atualizacao);
    }

    public void removerCliente(@PathVariable Long id){
        var cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }

    public Cliente buscarPorId(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
    }
}
