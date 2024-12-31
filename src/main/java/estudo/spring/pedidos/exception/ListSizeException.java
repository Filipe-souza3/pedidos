package estudo.spring.pedidos.exception;

import java.util.List;

public class ListSizeException extends RuntimeException {

    private String erro;
    private List<String> fields;
    

    public ListSizeException(String msg, String erro) {
        super(msg);
        this.erro = erro;
    }

    public ListSizeException(String msg, String erro, List<String> fields){
        super(msg);
        this.erro = erro;
        this.fields = fields;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    

}
