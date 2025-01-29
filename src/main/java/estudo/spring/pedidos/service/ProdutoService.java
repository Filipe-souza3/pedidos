package estudo.spring.pedidos.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import estudo.spring.pedidos.modal.ProdutoModel;
import estudo.spring.pedidos.repository.PedidoRepository;
import estudo.spring.pedidos.repository.ProdutoRepository;
import estudo.spring.pedidos.validator.ProdutoValidator;

@Service
public class ProdutoService {

    private final ProdutoRepository produtosRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoValidator produtoValidator;

    public ProdutoService(ProdutoRepository produtosRepository, PedidoRepository pedidoRepository,
            ProdutoValidator produtoValidator) {
        this.produtosRepository = produtosRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoValidator = produtoValidator;
    }

    public Page<ProdutoModel> list(Integer pagina, Integer qtdPagina, Integer id, String nome, String descricao,
            Double preco,
            Integer estoque) {

        PageRequest pageable = PageRequest.of(pagina, qtdPagina);

        ProdutoModel model = this.checkParamsList(id, nome, descricao, preco, estoque);
        if (model == null) {
            return this.produtosRepository.findAll(pageable);
        }

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);
        Example<ProdutoModel> example = Example.of(model, matcher);

        return this.produtosRepository.findAll(example, pageable);
    }

    public ProdutoModel register(ProdutoModel model) {
        this.produtoValidator.nameExist(model.getNome());
        return this.produtosRepository.save(model);
    }

    public ProdutoModel update(ProdutoModel model) {
        this.produtoValidator.exist(model.getId());
        return this.produtosRepository.save(model);
    }

    @Transactional
    public void delete(Integer id) {
        this.produtoValidator.exist(id);
        // List<Object> listPedidos =
        // this.pedidoRepository.findPedidosWithProdutosX(id);
        // listPedidos.forEach((e) -> {
        // Object[] pedido = (Object[]) e;
        // Integer pedidoId = (Integer) pedido[0];
        // this.pedidoRepository.deleteById(pedidoId);
        // });

        this.pedidoRepository.deletePedidosWithProdutosX(id);
        this.produtosRepository.deleteById(id);
    }

    private ProdutoModel checkParamsList(Integer id, String nome, String descricao, Double preco,
            Integer estoque) {
        ProdutoModel model = new ProdutoModel();
        int check = 0;
        if (id != null && id > 0) {
            model.setId(id);
            check++;
        }
        if (nome != null) {
            model.setNome(nome);
            check++;
        }
        if (descricao != null) {
            model.setDescricao(descricao);
            check++;
        }
        if (preco != null && preco > 0) {
            model.setPreco(preco);
            check++;
        }
        if (estoque != null && estoque > 0) {
            model.setEstoque(estoque);
            check++;
        }

        if (check == 0) {
            return null;
        }

        return model;
    }
}
