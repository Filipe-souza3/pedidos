package estudo.spring.pedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import estudo.spring.pedidos.modal.ProdutoModel;
import estudo.spring.pedidos.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtosRepository;

    public ProdutoService(ProdutoRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    public Optional<ProdutoModel> findById(Integer id){
        return this.produtosRepository.findById(id);
    }

    public List<ProdutoModel> list() {
        return this.produtosRepository.findAll();
    }

    public ProdutoModel register(ProdutoModel model) {
        return this.produtosRepository.save(model);
    }

    public ProdutoModel update(ProdutoModel model) {
        return this.produtosRepository.save(model);
    }
}
