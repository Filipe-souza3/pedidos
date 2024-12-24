package estudo.spring.pedidos.modal;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class PedidoModel {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "pedido_id")
    private Integer pedidoId;

    @JoinColumn(name = "produto_id")
    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    private ProdutoModel produto;

    @JoinColumn(name = "cliente_id")
    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    private ClienteModel cliente;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    public PedidoModel() {
    }

    public PedidoModel(Integer id, Integer pedidoId, ProdutoModel produto, ClienteModel cliente, Integer quantidade,
            LocalDate dataPedido) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.produto = produto;
        this.cliente = cliente;
        this.quantidade = quantidade;
        this.dataPedido = dataPedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

}
