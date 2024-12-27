package estudo.spring.pedidos.dto;

import java.util.List;

public class ResponseErro {

    private String status;
    private String message;
    private List<String> fields;
    private String error;

    public ResponseErro() {
    }

    // public static ResponseErro ResponseErro(String status, String error, List<String> fields, String message) {
    //     ResponseErro erro = new ResponseErro();
    //     erro.setStatus(status);
    //     erro.setError(error);
    //     erro.setFields(fields);
    //     erro.setMessage(message);
    //     return erro;
    // }

    

    public String getStatus() {
        return status;
    }

    public ResponseErro(String status, String error, List<String> fields, String message) {
        this.status = status;
        this.error = error;
        this.fields = fields;
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    







}
