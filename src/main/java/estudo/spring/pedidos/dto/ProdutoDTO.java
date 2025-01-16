package estudo.spring.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import estudo.spring.pedidos.modal.ProdutoModel;
import jakarta.validation.constraints.NotNull;

@JsonInclude(Include.NON_NULL)
public class ProdutoDTO {

    private Integer id;
    private String nome;
    @NotNull
    private String descricao;
    private Double preco;
    private Integer quantidade;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Integer id, String nome, String descricao, Double preco, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public ProdutoDTO produtoModeltoDto(ProdutoModel produtoModel, Integer quantidade) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produtoModel.getId());
        dto.setNome(produtoModel.getNome());
        dto.setDescricao(produtoModel.getDescricao());
        dto.setPreco(produtoModel.getPreco());
        if(quantidade != null){
            dto.setQuantidade(quantidade);
        }
        return dto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
