package estudo.spring.pedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import estudo.spring.pedidos.modal.PedidoModel;
import estudo.spring.pedidos.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Optional<PedidoModel> findById(Integer id){
        return this.pedidoRepository.findById(id);
    }

    public List<PedidoModel> list() {
        return this.pedidoRepository.findAllByOrderByPedidoId();
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

    public PedidoModel update(PedidoModel model){
        return this.pedidoRepository.save(model);
    }

    public void delete(Integer id){
        this.pedidoRepository.deleteById(id);
    }

}
