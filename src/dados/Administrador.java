package dados;

public class Administrador extends Pessoa {

    public Administrador(String nome, String cpf, String senha) {
        super(nome, cpf, senha);
    }

    @Override
    public String getPerfil() { return "Administrador"; }
}