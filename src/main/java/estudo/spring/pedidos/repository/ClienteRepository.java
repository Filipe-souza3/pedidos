package estudo.spring.pedidos.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import estudo.spring.pedidos.modal.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {

    public List<ClienteModel> findByEmail(String email);
}
