package estudo.spring.pedidos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import estudo.spring.pedidos.modal.PedidoModel;
import estudo.spring.pedidos.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoModel> list(){
        return this.pedidoRepository.findAllByOrderByPedidoId();
    }

    

}
