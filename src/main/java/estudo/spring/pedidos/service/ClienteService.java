package estudo.spring.pedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import estudo.spring.pedidos.modal.ClienteModel;
import estudo.spring.pedidos.modal.TelefoneModel;
import estudo.spring.pedidos.repository.ClienteRepository;
import estudo.spring.pedidos.repository.PedidoRepository;
import estudo.spring.pedidos.repository.TelefoneRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final TelefoneRepository telefoneRepository;
    private final PedidoRepository pedidoRepository;

    public ClienteService(ClienteRepository clienteRepository, TelefoneRepository telefoneRepository,
            PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.telefoneRepository = telefoneRepository;
        this.pedidoRepository = pedidoRepository;
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

   
    @Transactional
    @Modifying
    public void delete(Integer id) {

        // List<Object> listPedidos = this.pedidoRepository.findPedidosWithClientesX(id);
        // listPedidos.forEach((p) -> {
        //     Object[] pedido = (Object[]) p;
        //     Integer pedidoId = (Integer) pedido[0];
        //     this.pedidoRepository.deleteById(pedidoId);
        // });

        this.pedidoRepository.deletePedidosWithClientesX(id);
        this.telefoneRepository.deleteTelWithClienteX(id);
        this.clienteRepository.deleteById(id);
    }

}
