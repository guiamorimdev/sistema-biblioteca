package negocios;
public class ItemNaoEncontradoException extends Exception {
    public ItemNaoEncontradoException(String titulo){
        super ("Item nao encontrado: " + titulo);
    }
}
