package estudo.spring.pedidos.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonInclude;

import estudo.spring.pedidos.modal.PedidoModel;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoDTO {

    private Integer pedidoId;
    private List<ProdutoDTO> produtos;
    private ClienteDTO cliente;
    @Nullable
    private Integer quantidade;
    private LocalDate dataPedido;

    public PedidoDTO() {
        this.produtos = new ArrayList<ProdutoDTO>();
    }

    public PedidoDTO(Integer pedidoId, List<ProdutoDTO> produtos, ClienteDTO cliente, 
            LocalDate dataPedido) {
        this.pedidoId = pedidoId;
        this.produtos = produtos;
        this.cliente = cliente;
        this.dataPedido = dataPedido;

    }

    public PedidoDTO pedidoModelToDTO(PedidoModel model) {
        List<ProdutoDTO> listProdutos = new ArrayList<>();
        ProdutoDTO produtoDTO = new ProdutoDTO().produtoModeltoDto(model.getProduto(), model.getQuantidade());
        listProdutos.add(produtoDTO);
        PedidoDTO dto = new PedidoDTO(model.getPedidoId(), listProdutos, new ClienteDTO().clienteModelToDTO(model.getCliente()), model.getDataPedido());

        return dto;
    }

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

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

}
