package dados;
public class Livro extends ItemAcervo implements Emprestavel {
    private int numeroPaginas;
    private String isbn;
    private int quantidade;

    public Livro(String titulo, String autor, int ano, int numeroPaginas, String isbn, int quantidade) {
        super(titulo, autor, ano);
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.quantidade = quantidade;
    }
    
    // registro de emprestimo, multas, histórico. Classe de emprestimo,...
    
    @Override
    public String getTipo(){
        return "Livro";
    }
    
    @Override
    public void emprestar(){
        this.quantidade--;
    }
    
    @Override
    public void devolver(){
        this.quantidade++;
    }
    
    @Override
    public boolean isDisponivel(){
        return quantidade > 0;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }
    
    public int getQuantidade(){
        return quantidade;
    }

    @Override
    public String toString() {
        String status;
        if (quantidade > 0){
            status = "Disponivel (" + quantidade + ")";
        } else {
            status = "Indisponivel";
        }
        return super.toString() + numeroPaginas + " pgs | " + isbn + " | " + status;
    }
    
    
}
