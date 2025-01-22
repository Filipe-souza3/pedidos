package estudo.spring.pedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import estudo.spring.pedidos.modal.ProdutoModel;
import estudo.spring.pedidos.repository.PedidoRepository;
import estudo.spring.pedidos.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtosRepository;
    private final PedidoRepository pedidoRepository;

    public ProdutoService(ProdutoRepository produtosRepository, PedidoRepository pedidoRepository) {
        this.produtosRepository = produtosRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public Optional<ProdutoModel> findById(Integer id) {
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

    @Transactional
    public void delete(Integer id) {

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
}
