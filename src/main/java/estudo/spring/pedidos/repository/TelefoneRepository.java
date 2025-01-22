package estudo.spring.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import estudo.spring.pedidos.modal.TelefoneModel;

public interface TelefoneRepository extends JpaRepository<TelefoneModel, String> {

    @Transactional
    @Modifying
    @Query(value = """
                delete from telefones where id_cliente = ?1
            """, nativeQuery = true)
    public void deleteTelWithClienteX(Integer id);

}
