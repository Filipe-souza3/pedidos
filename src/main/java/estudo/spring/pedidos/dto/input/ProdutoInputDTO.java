package estudo.spring.pedidos.dto.input;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import estudo.spring.pedidos.modal.ProdutoModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoInputDTO {

    @NotBlank(message = "Campo nome é obrigatório.")
    @Length(min = 4, max = 100, message = "Minimo é {min} e o máximo é {max}.")
    private String nome;
    @NotBlank(message = "Campo descrição é obrigatório.")
    private String descricao;
    @NotNull(message = "Campo preço é obrigatório.")
    @Range(min = 0, max = 1000, message = "Valor máximo é 1000 reais.")
    private Double preco;
    @NotNull(message = "Campo estoque é obrigatório.")
    @Range(min = 1, max = 1000, message = "Minimo é {min} e o máximo é {max}.")
    private Integer estoque;

    public ProdutoInputDTO() {
    }

    public ProdutoInputDTO(String nome, String descricao, Double preco, Integer estoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    public ProdutoModel produtoDTOToModel() {
        ProdutoModel model = new ProdutoModel();
        model.setNome(this.nome);
        model.setDescricao(this.descricao);
        model.setPreco(this.preco);
        model.setEstoque(estoque);
        return model;
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

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

}
