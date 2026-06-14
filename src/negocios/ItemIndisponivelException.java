package negocios;
public class ItemIndisponivelException extends Exception {
    public ItemIndisponivelException (String titulo){
        super("Item indisponivel: " + titulo + " já está emprestado.");
    }
}
