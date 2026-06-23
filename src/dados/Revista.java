package dados;
public class Revista extends ItemAcervo implements Emprestavel{
    private int edicao;
    private String mes;
    private int quantidade;

    public Revista(String titulo, String autor, int ano, int edicao, String mes, int quantidade) {
        super(titulo, autor, ano);
        this.edicao = edicao;
        this.mes = mes;
        this.quantidade = quantidade;
    }
    
    @Override
    public String getTipo(){
        return "Revista";
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

    public int getEdicao() {
        return edicao;
    }

    public String getMes() {
        return mes;
    }
    
    public int getQuantidade() { 
        return quantidade; 
    }
    
    public String toString(){
        String status;
        if (quantidade > 0){
            status = "Disponivel (" + quantidade + ")";
        } else {
            status = "Indisponivel";
        }
        return super.toString() + edicao + " | " + mes + " | " + status;
    }
    
}
