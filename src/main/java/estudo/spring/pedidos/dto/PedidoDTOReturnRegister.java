package estudo.spring.pedidos.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoDTOReturnRegister {

    private Integer id;
    private Integer pedidoId;
    private ProdutoDTO produto;
    private ClienteDTO cliente;
    private Integer quantidade;
    private LocalDate dataPedido;

    public PedidoDTOReturnRegister() {
    }

    public PedidoDTOReturnRegister(Integer id, Integer pedidoId, ProdutoDTO produto, ClienteDTO cliente,
            Integer quantidade, LocalDate dataPedido) {
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

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
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

    



    

}
