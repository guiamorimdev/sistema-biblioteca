package dados;
public class Livro extends ItemAcervo implements Emprestavel {
    private int numeroPaginas;
    private String isbn;
    private boolean disponivel;

    public Livro(String titulo, String autor, int ano, int numeroPaginas, String isbn) {
        super(titulo, autor, ano);
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.disponivel = true;
    }
    
    // registro de emprestimo, multas, histórico. Classe de emprestimo,...
    
    @Override
    public String getTipo(){
        return "Livro";
    }
    
    @Override
    public void emprestar(){
        this.disponivel = false;
    }
    
    @Override
    public void devolver(){
        this.disponivel = true;
    }
    
    @Override
    public boolean isDisponivel(){
        return disponivel;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        String status;
        if (disponivel == true){
            status = "Disponivel";
        } else {
            status = "Emprestado";
        }
        return super.toString() + numeroPaginas + " pgs | " + isbn + " | " + status;
    }
    
    
}
