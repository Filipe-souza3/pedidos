package estudo.spring.pedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import estudo.spring.pedidos.modal.ClienteModel;
import estudo.spring.pedidos.modal.TelefoneModel;
import estudo.spring.pedidos.repository.ClienteRepository;
import estudo.spring.pedidos.repository.TelefoneRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final TelefoneRepository telefoneRepository;

    public ClienteService(ClienteRepository clienteRepository, TelefoneRepository telefoneRepository) {
        this.clienteRepository = clienteRepository;
        this.telefoneRepository = telefoneRepository;
    }

    public Optional<ClienteModel> findById(Integer id) {
        return this.clienteRepository.findById(id);
    }

    public List<ClienteModel> list() {
        return this.clienteRepository.findAll();
    }

    @Transactional
    public ClienteModel register(ClienteModel model) {
        ClienteModel cliente = this.clienteRepository.save(model);
        model.getTelefones().stream().forEach(t -> {
            TelefoneModel tel = new TelefoneModel();
            tel.setTelefone(t.getTelefone());
            tel.setCliente(cliente);
            this.telefoneRepository.save(tel);

        });
        return model;
    }

    @Transactional
    public ClienteModel update(ClienteModel modal) {
        ClienteModel cliente = this.clienteRepository.save(modal);
        modal.getTelefones().stream().forEach(t -> {
            t.setCliente(modal);
            this.telefoneRepository.save(t);
        });
        cliente.setTelefones(modal.getTelefones());
        return modal;
    }

}
