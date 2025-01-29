package estudo.spring.pedidos.validator;

import org.springframework.stereotype.Component;

import estudo.spring.pedidos.exception.GenericException;
import estudo.spring.pedidos.repository.ProdutoRepository;

@Component
public class ProdutoValidator {

    private final ProdutoRepository produtoRepository;

    public ProdutoValidator(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void exist(Integer id) {
        if (!(this.produtoRepository.findById(id)).isPresent()) {
            throw new GenericException("Produto não econtrado.", "O produto " + id + " não foi encontrado na base.");
        }
    }

    public void nameExist(String name) {
        if ((this.produtoRepository.findByNome(name)).size() > 0) {
            throw new GenericException("Produto existente", "O produto " + name + " já existe na base.");
        }
    }

}
