package negocios;
public class UsuarioNaoEncontradoException extends Exception{
    public UsuarioNaoEncontradoException(String cpf){
        super("Usuario nao encontrado: " + cpf);
    }
}
