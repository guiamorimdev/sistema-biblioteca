package dados;

public class UsuarioComum extends Pessoa {
    private int totalEmprestimos;

    public UsuarioComum(String nome, String cpf, String senha) {
        super(nome, cpf, senha);
        this.totalEmprestimos = 0;
    }

    public void incrementarEmprestimos() { totalEmprestimos++; }
    public int getTotalEmprestimos()     { return totalEmprestimos; }

    @Override
    public String getPerfil() { return "Usuário Comum"; }
}