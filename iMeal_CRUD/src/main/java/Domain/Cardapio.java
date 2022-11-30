package Domain;

import java.time.LocalDate;

public class Cardapio {
    private Integer id;
    private Refeicao refeicao;
    private LocalDate data;
    private String descricao;

    public Cardapio() {
    }

    public Cardapio(Integer id, Refeicao refeicao, LocalDate data, String descricao) {
        this.id = id;
        this.refeicao = refeicao;
        this.data = data;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Refeicao getRefeicao() {
        return refeicao;
    }

    public void setRefeicao(Refeicao refeicao) {
        this.refeicao = refeicao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
    
}
