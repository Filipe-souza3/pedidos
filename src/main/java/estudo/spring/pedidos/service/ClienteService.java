package estudo.spring.pedidos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import estudo.spring.pedidos.modal.ClienteModel;
import estudo.spring.pedidos.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteModel> list(){
       return this.clienteRepository.findAll();
    }
}
