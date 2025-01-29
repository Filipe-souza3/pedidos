package estudo.spring.pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import estudo.spring.pedidos.modal.ProdutoModel;


public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {

    public List<ProdutoModel> findByNome(String nome);

}
