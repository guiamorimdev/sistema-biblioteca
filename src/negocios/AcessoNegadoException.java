package negocios;

public class AcessoNegadoException extends Exception {
    public AcessoNegadoException() {
        super("Acesso negado. Você não tem permissão para esta ação.");
    }
}