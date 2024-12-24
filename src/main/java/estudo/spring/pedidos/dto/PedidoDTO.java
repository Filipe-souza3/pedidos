package estudo.spring.pedidos.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import estudo.spring.pedidos.modal.ClienteModel;

public class PedidoDTO {
    private Integer pedidoId;
    private List<ProdutoDTO> produtos;
    private ClienteModel cliente;
    // private Integer quantidade;
    private LocalDate dataPedido;

    public PedidoDTO() {
        this.produtos = new ArrayList<ProdutoDTO>();
    }

    public PedidoDTO(Integer pedidoId, List<ProdutoDTO> produtos, ClienteModel cliente, 
            LocalDate dataPedido) {
        this.pedidoId = pedidoId;
        this.produtos = produtos;
        this.cliente = cliente;
        this.dataPedido = dataPedido;

    }

    // public PedidoDTO pedidoModelToDTO(PedidoModel pedido) {
    //     PedidoDTO dto = new PedidoDTO(pedido.getPedidoId(), this.produtos, pedido.getCliente(), pedido.getQuantidade(),
    //             pedido.getDataPedido());

    //     return dto;
    // }

    public void addListProdutos(ProdutoDTO produto) {
        this.produtos.add(produto);
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

}
