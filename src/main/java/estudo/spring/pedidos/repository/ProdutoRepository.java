package estudo.spring.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import estudo.spring.pedidos.modal.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {

}
