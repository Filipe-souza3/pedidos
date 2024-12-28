package estudo.spring.pedidos.dto.input;

import estudo.spring.pedidos.modal.TelefoneModel;

public class TelefoneInputDTO {

    private String telefone;

    public TelefoneInputDTO() {
    }

    public TelefoneInputDTO(String telefone) {
        this.telefone = telefone;
    }

    public TelefoneModel telefoneDTOToModel() {
        TelefoneModel model = new TelefoneModel();
        model.setTelefone(this.telefone);
        return model;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
