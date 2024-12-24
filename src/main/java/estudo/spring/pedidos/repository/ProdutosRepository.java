package estudo.spring.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import estudo.spring.pedidos.modal.ProdutoModel;

public interface ProdutosRepository extends JpaRepository<ProdutoModel, Integer> {

}
