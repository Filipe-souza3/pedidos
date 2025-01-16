package estudo.spring.pedidos.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import estudo.spring.pedidos.dto.ResponseErro;

@RestControllerAdvice
public class GlobalException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseErro methodArgumentNotValidException(MethodArgumentNotValidException e){
        ResponseErro erro = new ResponseErro();
        erro.setStatus(e.getStatusCode().toString());
        erro.setError(e.getMessage());
        List<String> erroFields = e.getFieldErrors().stream().map(f -> f.getField() +" - "+ f.getDefaultMessage()).collect(Collectors.toList());
        erro.setFields(erroFields);
        erro.setMessage("Algum campo esta invalido.");
        return erro;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ListSizeException.class)
    public ResponseErro listSizeException(ListSizeException e){
        ResponseErro erro = new ResponseErro();
        erro.setStatus(HttpStatus.BAD_REQUEST.toString());
        erro.setMessage(e.getMessage());
        erro.setError(e.getErro());
        if(e.getFields().size() >0){
            erro.setFields(e.getFields());
        }
        return erro;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GenericException.class)
    public ResponseErro genericException(GenericException e){
        ResponseErro erro = new ResponseErro();
        erro.setStatus(HttpStatus.BAD_REQUEST.toString());
        erro.setMessage(e.getMessage());
        erro.setError(e.getErro());
        erro.setFields(List.of());
        return erro;
    }

}
