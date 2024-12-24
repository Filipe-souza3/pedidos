package estudo.spring.pedidos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import estudo.spring.pedidos.modal.ProdutoModel;
import estudo.spring.pedidos.repository.ProdutosRepository;

@Service
public class ProdutoService {

  
    private final ProdutosRepository produtosRepository;

    public ProdutoService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    public List<ProdutoModel> list(){
        return this.produtosRepository.findAll();
    }
}
