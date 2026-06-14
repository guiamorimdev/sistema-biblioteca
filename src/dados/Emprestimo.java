package dados;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private ItemAcervo item;
    private String nomeUsuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private static final double MULTA_POR_DIA = 1.00;

    public Emprestimo(ItemAcervo item, String nomeUsuario, LocalDate dataEmprestimo, LocalDate dataPrevistaDevolucao) {
        this.item = item;
        this.nomeUsuario = nomeUsuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataDevolucao = null;
    }
    
    public void registrarDevolucao(){
        this.dataDevolucao = LocalDate.now();
    }
    
    public boolean isDevolvido(){
        return dataDevolucao != null;
    }
    
    public double calcularMulta(){
        LocalDate dataReferencia;
        if (isDevolvido()){
            dataReferencia = dataDevolucao;
        } else {
            dataReferencia = LocalDate.now();
        }
        
        long diasAtraso = ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataReferencia);
        
        if (diasAtraso <= 0){
            return 0.0;
        }
        
        return diasAtraso * MULTA_POR_DIA;
    }

    public ItemAcervo getItem() {
        return item;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    @Override
    public String toString() {
        String status;
        if (isDevolvido())
            status = "Devolvido em " + dataDevolucao;
        else
            status = "Em aberto";
        return nomeUsuario + " | " + item.getTitulo()
                + " | Retirado: " + dataEmprestimo
                + " | Devolucao prevista: " + dataPrevistaDevolucao
                + " | " + status
                + " | Multa R$ " + String.format("%.2f", calcularMulta());
    }
    
    
    
}
