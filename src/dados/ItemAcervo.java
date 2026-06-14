package dados;
public abstract class ItemAcervo {
    protected String titulo;
    protected String autor;
    protected int ano;

    public ItemAcervo(String titulo, String autor, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public abstract String getTipo();

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }

    @Override
    public String toString() {
        return getTipo() + " | " + titulo + " | " + autor + " | " + ano + " | ";
    }
}
