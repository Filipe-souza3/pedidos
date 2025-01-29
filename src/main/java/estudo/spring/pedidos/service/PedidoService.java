package estudo.spring.pedidos.service;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import estudo.spring.pedidos.modal.ClienteModel;
import estudo.spring.pedidos.modal.PedidoModel;
import estudo.spring.pedidos.modal.ProdutoModel;
import estudo.spring.pedidos.repository.PedidoRepository;
import estudo.spring.pedidos.validator.PedidoValidator;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoValidator pedidoValidator;

    public PedidoService(PedidoRepository pedidoRepository, PedidoValidator pedidoValidator) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoValidator = pedidoValidator;
    }

    // public Optional<PedidoModel> findById(Integer id) {
    // return this.pedidoRepository.findById(id);
    // }

    public Page<PedidoModel> list(Integer pagina, Integer qtdPagina, Integer produtoId, Integer clienteId,
            Integer quantidade, Integer pedidoId) {
        
        Pageable page = PageRequest.of(pagina, qtdPagina);
        
        PedidoModel model = this.checkParamsList(produtoId, clienteId, quantidade, pedidoId);

        if(model == null){
            return this.pedidoRepository.findAll(page);
        }

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues()
                .withStringMatcher(StringMatcher.CONTAINING);
        Example<PedidoModel> example = Example.of(model, matcher);

        return this.pedidoRepository.findAll(example, page);
    }

    public List<PedidoModel> register(List<PedidoModel> listModel) {
        Integer sequence = this.pedidoRepository.getSequence();
        listModel.forEach((m) -> {

            /** RETORNANDO OBJECT E PEGANDO AS VALORES */
            // Object result = this.pedidoRepository.register(m.getProduto().getId(),
            // m.getCliente().getId(),
            // m.getQuantidade(), sequence);

            // Object[] objs = (Object[]) result;
            // m.setPedidoId((Integer) objs[5]);
            // m.setId((Integer) objs[0]);

            Integer result = this.pedidoRepository.register(m.getProduto().getId(), m.getCliente().getId(),
                    m.getQuantidade(), sequence);

            m.setPedidoId(sequence);
            m.setId(result);
        });
        return listModel;
    }

    public PedidoModel update(PedidoModel model) {
        this.pedidoValidator.exist(model.getId());
        Optional<PedidoModel> optional = this.pedidoRepository.findById(model.getId());
        if (optional.isPresent()) {
            model.setPedidoId(optional.get().getPedidoId());
        }
        return this.pedidoRepository.save(model);
    }

    public void delete(Integer id) {
        this.pedidoValidator.exist(id);
        this.pedidoRepository.deleteById(id);
    }

    private PedidoModel checkParamsList(Integer produtoId, Integer clienteId,
            Integer quantidade, Integer pedidoId) {

        PedidoModel model = new PedidoModel();
        boolean checkParams = false;

        if (pedidoId != null) {
            model.setPedidoId(pedidoId);
            checkParams = true;
        }

        if (clienteId != null) {
            ClienteModel cliente = new ClienteModel();
            cliente.setId(clienteId);
            model.setCliente(cliente);
            checkParams = true;
        }

        if (quantidade != null && quantidade > 0) {
            model.setQuantidade(quantidade);
            checkParams = true;
        }
        if (produtoId != null) {
            ProdutoModel produto = new ProdutoModel();
            produto.setId(produtoId);
            model.setProduto(produto);
            checkParams = true;
        }

        if (checkParams == false) {
            return null;
        }

        return model;
    }

}
