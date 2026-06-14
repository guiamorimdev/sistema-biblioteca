package dados;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected String senha;

    public Pessoa(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public abstract String getPerfil(); // "Administrador" ou "Usuário Comum"

    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public String getNome() { return nome; }
    public String getCpf()  { return cpf; }

    @Override
    public String toString() {
        return getPerfil() + " | " + nome + " | CPF: " + cpf;
    }
}