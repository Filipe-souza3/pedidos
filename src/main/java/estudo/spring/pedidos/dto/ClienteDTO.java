package estudo.spring.pedidos.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import estudo.spring.pedidos.dto.input.TelefoneInputDTO;
import estudo.spring.pedidos.modal.ClienteModel;

public class ClienteDTO {
    private String nome;
    private String email;
    private String endereco;
    private Collection<TelefoneInputDTO> listaTelefone;

    public ClienteDTO() {
    }

    public ClienteDTO(String nome, String email, String endereco, Collection<TelefoneInputDTO> listaTelefone) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.listaTelefone = listaTelefone;
    }

    public ClienteDTO clienteModelToDTO(ClienteModel model) {
        ClienteDTO dto = new ClienteDTO();
        dto.setNome(model.getNome());
        dto.setEmail(model.getEmail());
        dto.setEndereco(model.getEndereco());
        List<TelefoneInputDTO> listTel = new ArrayList<>();
            model.getTelefones().forEach((t)->{
            TelefoneInputDTO telInput = new TelefoneInputDTO();
            telInput.setTelefone(t.getTelefone());
            listTel.add(telInput);
        });
        dto.setListaTelefone(listTel);

        return dto;
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

    public void setListaTelefone(Collection<TelefoneInputDTO> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }

}