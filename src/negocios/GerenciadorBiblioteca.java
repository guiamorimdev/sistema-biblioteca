package negocios;

import dados.Administrador;
import dados.Emprestimo;
import dados.Emprestavel;
import dados.ItemAcervo;
import dados.Pessoa;
import java.time.LocalDate;
import dados.UsuarioComum;
import java.util.ArrayList;
import dados.Livro;
import dados.Revista;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GerenciadorBiblioteca {

    private ArrayList<ItemAcervo> acervo;
    private ArrayList<Emprestimo> emprestimos;
    private ArrayList<Pessoa> usuarios;
    private Pessoa pessoaLogada;

    public GerenciadorBiblioteca() {
        this.acervo = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.pessoaLogada = null;
        
        // usuarios padrão do sistema
        usuarios.add(new Administrador("Admin", "000.000.000-00", "admin123"));
        usuarios.add(new UsuarioComum("user", "111.111.111-11", "user123"));
    }

    public void login(String cpf, String senha) throws SenhaIncorretaException, UsuarioNaoEncontradoException{
        for (Pessoa p : usuarios){
            if (p.getCpf().equals(cpf)){
                if (!p.verificarSenha(senha)){
                    throw new SenhaIncorretaException();
                }
                this.pessoaLogada = p;
                return;
            }
        }
        throw new UsuarioNaoEncontradoException(cpf);
    }

    public void logout(){
        this.pessoaLogada = null;
    }
    
    public Pessoa getPessoaLogada() { return pessoaLogada; }

    public boolean isAdministrador() {
        return pessoaLogada instanceof Administrador;
    }
    
    // Adiciona item ao acervo
    public void adicionarItem(ItemAcervo item) {
        acervo.add(item);
    }

    // Busca item pelo título
    public ItemAcervo buscarPorTitulo(String titulo) throws ItemNaoEncontradoException {
        for (ItemAcervo item : acervo) {
            if (item.getTitulo().equalsIgnoreCase(titulo)) {
                return item;
            }
        }
        throw new ItemNaoEncontradoException(titulo);
    }

    // Realiza empréstimo
    public Emprestimo realizarEmprestimo(String titulo, String nomeUsuario, LocalDate dataPrevistaDevolucao)
            throws ItemNaoEncontradoException, ItemIndisponivelException {

        ItemAcervo item = buscarPorTitulo(titulo);

        if (item instanceof Emprestavel) {
            Emprestavel emprestavel = (Emprestavel) item;

            if (!emprestavel.isDisponivel()) {
                throw new ItemIndisponivelException(titulo);
            }

            emprestavel.emprestar();
            Emprestimo emp = new Emprestimo(item, nomeUsuario, LocalDate.now(), dataPrevistaDevolucao);
            emprestimos.add(emp);
            return emp;
        }

        throw new ItemIndisponivelException(titulo);
    }

    // Realiza devolução
    public void realizarDevolucao(String titulo)
            throws ItemNaoEncontradoException, ItemIndisponivelException {

        ItemAcervo item = buscarPorTitulo(titulo);

        for (Emprestimo emp : emprestimos) {
            if (emp.getItem() == item && !emp.isDevolvido()) {
                emp.registrarDevolucao();
                ((Emprestavel) item).devolver();
                return;
            }
        }

        throw new ItemIndisponivelException(titulo);
    }

    // Lista todo o acervo
    public void listarAcervo() {
        System.out.println("=== ACERVO ===");
        for (ItemAcervo item : acervo) {
            System.out.println(item);
        }
    }

    // Lista todos os empréstimos
    public void listarEmprestimos() {
        System.out.println("=== EMPRÉSTIMOS ===");
        for (Emprestimo emp : emprestimos) {
            System.out.println(emp);
        }
    }
    
    public void salvarAcervo() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("acervo.txt"))) {
        for (ItemAcervo item : acervo) {
            if (item instanceof Livro) {
                Livro l = (Livro) item;
                bw.write("LIVRO;" + l.getTitulo() + ";" + l.getAutor() + ";" + l.getAno() + ";" + l.getNumeroPaginas() + ";" + l.getIsbn() + ";" + l.getQuantidade());
            } else if (item instanceof Revista) {
                Revista r = (Revista) item;
                bw.write("REVISTA;" + r.getTitulo() + ";" + r.getAutor() + ";" + r.getAno() + ";" + r.getEdicao() + ";" + r.getMes() + ";" + r.getQuantidade());
            }
            bw.newLine();
        }
    } catch (IOException e) {
        System.out.println("Erro ao salvar: " + e.getMessage());
    }
    }

    public void carregarAcervo() {
        try (BufferedReader br = new BufferedReader(new FileReader("acervo.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes[0].equals("LIVRO")) {
                    acervo.add(new Livro(partes[1], partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), partes[5], Integer.parseInt(partes[6])));
                } else if (partes[0].equals("REVISTA")) {
                    acervo.add(new Revista(partes[1], partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), partes[5], Integer.parseInt(partes[6])));
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado, iniciando com acervo vazio.");
        }
    }
    
    public ArrayList<ItemAcervo> getAcervo()         { return acervo; }
    public ArrayList<Emprestimo> getEmprestimos()     { return emprestimos; }
}