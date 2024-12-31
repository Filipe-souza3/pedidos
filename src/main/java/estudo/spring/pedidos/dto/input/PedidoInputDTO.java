package estudo.spring.pedidos.dto.input;

import estudo.spring.pedidos.modal.ClienteModel;
import estudo.spring.pedidos.modal.PedidoModel;
import estudo.spring.pedidos.modal.ProdutoModel;

public class PedidoInputDTO {

    private Integer produtoId;
    private Integer clienteId;
    private Integer quantidade;
    
    public PedidoInputDTO() {
    }

    public PedidoInputDTO(Integer produtoId, Integer clienteId, Integer quantidade) {
        this.produtoId = produtoId;
        this.clienteId = clienteId;
        this.quantidade = quantidade;
    }

    public PedidoModel pedidoDTOToModel(){
        PedidoModel model = new PedidoModel();
        ClienteModel cliente = new ClienteModel();
        cliente.setId(this.clienteId);
        model.setCliente(cliente);
        ProdutoModel produto = new ProdutoModel();
        produto.setId(this.produtoId);
        model.setProduto(produto);
        model.setQuantidade(this.quantidade);
        return model;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    
}
