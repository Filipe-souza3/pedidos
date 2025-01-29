package estudo.spring.pedidos.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import estudo.spring.pedidos.modal.ClienteModel;
import estudo.spring.pedidos.modal.TelefoneModel;
import estudo.spring.pedidos.repository.ClienteRepository;
import estudo.spring.pedidos.repository.PedidoRepository;
import estudo.spring.pedidos.repository.TelefoneRepository;
import estudo.spring.pedidos.validator.ClienteValidator;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final TelefoneRepository telefoneRepository;
    private final PedidoRepository pedidoRepository;
    private final ClienteValidator clienteValidator;

    public ClienteService(ClienteRepository clienteRepository, TelefoneRepository telefoneRepository,
            PedidoRepository pedidoRepository, ClienteValidator clienteValidator) {
        this.clienteRepository = clienteRepository;
        this.telefoneRepository = telefoneRepository;
        this.pedidoRepository = pedidoRepository;
        this.clienteValidator = clienteValidator;
    }

    public Page<ClienteModel> list(Integer pagina, Integer qtdPagina, Integer id, String nome, String email,
            String endereco) {
        PageRequest pageRequest = PageRequest.of(pagina, qtdPagina);
        ClienteModel model = this.checkParamsList(id, nome, email, endereco);
        if (model == null) {
            return this.clienteRepository.findAll(pageRequest);
        }

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);
        Example<ClienteModel> example = Example.of(model, matcher);
        return this.clienteRepository.findAll(example, pageRequest);
    }

    @Transactional
    public ClienteModel register(ClienteModel model) {
        clienteValidator.EmailExist(model.getEmail());
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
    public ClienteModel update(ClienteModel model) {
        clienteValidator.exist(model.getId());
        clienteValidator.EmailExist(model.getEmail());

        ClienteModel cliente = this.clienteRepository.save(model);
        model.getTelefones().stream().forEach(t -> {
            t.setCliente(model);
            this.telefoneRepository.save(t);
        });
        cliente.setTelefones(model.getTelefones());
        return model;
    }

    @Transactional
    @Modifying
    public void delete(Integer id) {

        // List<Object> listPedidos =
        // this.pedidoRepository.findPedidosWithClientesX(id);
        // listPedidos.forEach((p) -> {
        // Object[] pedido = (Object[]) p;
        // Integer pedidoId = (Integer) pedido[0];
        // this.pedidoRepository.deleteById(pedidoId);
        // });
        this.clienteValidator.exist(id);
        this.pedidoRepository.deletePedidosWithClientesX(id);
        this.telefoneRepository.deleteTelWithClienteX(id);
        this.clienteRepository.deleteById(id);
    }

    private ClienteModel checkParamsList(Integer id, String nome, String email, String endereco) {
        ClienteModel model = new ClienteModel();
        int check = 0;

        if (id != null && id > 0) {
            model.setId(id);
            ;
            check++;
        }
        if (nome != null) {
            model.setNome(nome);
            check++;
        }
        if (email != null) {
            model.setEmail(email);
            check++;
        }
        if (endereco != null) {
            model.setEndereco(endereco);
            check++;
        }

        if (check == 0) {
            return null;
        }

        return model;
    }

}
