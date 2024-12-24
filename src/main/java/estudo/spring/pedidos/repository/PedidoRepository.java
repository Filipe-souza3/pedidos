package estudo.spring.pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import estudo.spring.pedidos.modal.PedidoModel;

public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {

    public List<PedidoModel> findAllByOrderByPedidoId();
}
