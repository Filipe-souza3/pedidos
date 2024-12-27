package estudo.spring.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import estudo.spring.pedidos.modal.TelefoneModel;

public interface TelefoneRepository extends JpaRepository<TelefoneModel, Integer> {

}
