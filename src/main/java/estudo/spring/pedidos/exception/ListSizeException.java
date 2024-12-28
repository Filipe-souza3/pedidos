package estudo.spring.pedidos.exception;

public class ListSizeException extends RuntimeException {

    private String err;
    

    public ListSizeException(String msg, String err) {
        super(msg);
        this.err = err;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

}
