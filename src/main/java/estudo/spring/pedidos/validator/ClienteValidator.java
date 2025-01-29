package estudo.spring.pedidos.validator;

import org.springframework.stereotype.Component;

import estudo.spring.pedidos.exception.GenericException;
import estudo.spring.pedidos.repository.ClienteRepository;

@Component

public class ClienteValidator {

    private final ClienteRepository clienteRepository;

    public ClienteValidator(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void exist(Integer id) {
        if (!(this.clienteRepository.findById(id)).isPresent()) {
            throw new GenericException("Cliente não encontrado.", "O cliente " + id + " não foi encontrado na base.");
        }
    }

    public void EmailExist(String email) {
        if (this.clienteRepository.findByEmail(email).size() > 0) {
            throw new GenericException("Email já existe.", "O email " + email + " já existe na base.");
        }
    }

}
