package estudo.spring.pedidos.dto.input;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import estudo.spring.pedidos.exception.ListSizeException;
import estudo.spring.pedidos.modal.ClienteModel;
import estudo.spring.pedidos.modal.TelefoneModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Component
public class ClienteInputDTO {

    @NotBlank(message = "Campo nome é obrigatório.")
    @Length(min = 5, max = 100, message = "Minimo é {min} e o máximo é {max}.")
    private String nome;
    @NotBlank(message = "Campo email é obrigatório.")
    @Email(message = "Esse email é inválido.")
    private String email;
    @NotBlank(message = "Campo endereço é obrigatório.")
    @Length(min = 5, max = 200, message = "Minimo é {min} e o máximo é {max}.")
    private String endereco;
    private Collection<TelefoneInputDTO> listaTelefone;

    public ClienteInputDTO() {
    }

    public ClienteInputDTO(String nome, String email, String endereco, List<TelefoneInputDTO> listaTelefone) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.listaTelefone = listaTelefone;
    }

    public ClienteModel clienteDTOToModel() {
        ClienteModel model = new ClienteModel();
        model.setNome(this.nome);
        model.setEmail(this.email);
        model.setEndereco(this.endereco);
        if (this.listaTelefone.size() < 0) {
            List<TelefoneModel> list = this.listaTelefone.stream().map(t -> t.telefoneDTOToModel())
                .collect(Collectors.toList());
            model.setTelefones(list);
        } else {
            throw new ListSizeException("Campo telefone é obrigatório", "");
        }

        return model;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Collection<TelefoneInputDTO> getListaTelefone() {
        return listaTelefone;
    }

    public void setListaTelefone(List<TelefoneInputDTO> listTelefone) {
        this.listaTelefone = listTelefone;
    }

}
