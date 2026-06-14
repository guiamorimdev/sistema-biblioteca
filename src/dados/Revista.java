package dados;
public class Revista extends ItemAcervo implements Emprestavel{
    private int edicao;
    private String mes;
    private boolean disponivel;

    public Revista(String titulo, String autor, int ano, int edicao, String mes) {
        super(titulo, autor, ano);
        this.edicao = edicao;
        this.mes = mes;
        this.disponivel = true;
    }
    
    @Override
    public String getTipo(){
        return "Revista";
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

    public int getEdicao() {
        return edicao;
    }

    public String getMes() {
        return mes;
    }
    
    public String toString(){
        String status;
        if (disponivel == true){
            status = "Disponivel";
        } else {
            status = "Emprestado";
        }
        return super.toString() + edicao + " | " + mes + " | " + status;
    }
    
}
