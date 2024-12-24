package estudo.spring.pedidos.modal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "telefones")
public class TelefoneModel {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "telefone")
    private String telefone;

    @JsonBackReference // para nao dar recursividade no objeto coloca no outro objeto tbm
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private ClienteModel clientes;

    public TelefoneModel() {
    }

    public TelefoneModel(Integer id, String telefone, ClienteModel clientes) {
        this.id = id;
        this.telefone = telefone;
        this.clientes = clientes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ClienteModel getClientes() {
        return clientes;
    }

    public void setClientes(ClienteModel clientes) {
        this.clientes = clientes;
    }

    
}
