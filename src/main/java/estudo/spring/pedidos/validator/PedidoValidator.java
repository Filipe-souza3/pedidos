package estudo.spring.pedidos.validator;

import org.springframework.stereotype.Component;

import estudo.spring.pedidos.exception.GenericException;
import estudo.spring.pedidos.repository.ClienteRepository;

@Component
public class PedidoValidator {

    private final ClienteRepository clienteRepository;

    public PedidoValidator(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void exist(Integer id) {
        if (!(this.clienteRepository.findById(id)).isPresent()) {
            throw new GenericException("Pedido não encontrado.", "O pedido " + id + " não foi encontrado na base.");
        }
    }
}
