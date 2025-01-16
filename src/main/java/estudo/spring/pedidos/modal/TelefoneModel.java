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
    @Column(name = "telefone")
    private String telefone;

    @JsonBackReference // para nao dar recursividade no objeto coloca no outro objeto tbm
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private ClienteModel cliente;

    public TelefoneModel() {
    }

    public TelefoneModel(String telefone, ClienteModel cliente) {

        this.telefone = telefone;
        this.cliente = cliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    
}
