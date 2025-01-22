package estudo.spring.pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import estudo.spring.pedidos.modal.PedidoModel;

public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {

    public List<PedidoModel> findAllByOrderByPedidoId();

    @Query(value = "select nextval('id_pedido')", nativeQuery = true)
    public Integer getSequence();

    @Transactional
    @Query(value = """
            insert into pedidos(produto_id, cliente_id, quantidade, pedido_id)
            values(?1, ?2, ?3, ?4) returning id
            """, nativeQuery = true)
    public Integer register(Integer produtoId, Integer clienteId, Integer quantidades, Integer sequence);

    @Query(value = """
            select * from pedidos where produto_id = ?1
            """, nativeQuery = true)
    public List<Object> findPedidosWithProdutosX(Integer id);

    @Transactional
    @Modifying
    @Query(value = """
                delete from pedidos where  produto_id = ?1
            """, nativeQuery = true)
    public void deletePedidosWithProdutosX(Integer id);

    @Query(value = """
            select * from  pedidos where cliente_id = ?1
            """, nativeQuery = true)
    public List<Object> findPedidosWithClientesX(Integer id);

    @Transactional
    @Modifying
    @Query(value = """
                delete from pedidos where cliente_id = ?1
            """, nativeQuery = true)
    public void deletePedidosWithClientesX(Integer id);
}
