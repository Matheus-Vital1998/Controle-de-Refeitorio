package Domain;

import java.time.LocalTime;

public class HistoricoConsumo {
    private Integer id;
    private Usuario usuario;
    private Cardapio cardapio;
    private LocalTime horarioChegada;
    private Boolean entradaAutorizada;
    private String motivo;

    public HistoricoConsumo() {
    }

    public HistoricoConsumo(Integer id, Usuario usuario, Cardapio cardapio, LocalTime horarioChegada, Boolean entradaAutorizada, String motivo) {
        this.id = id;
        this.usuario = usuario;
        this.cardapio = cardapio;
        this.horarioChegada = horarioChegada;
        this.entradaAutorizada = entradaAutorizada;
        this.motivo = motivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public LocalTime getHorarioChegada() {
        return horarioChegada;
    }

    public void setHorarioChegada(LocalTime horarioChegada) {
        this.horarioChegada = horarioChegada;
    }

    public Boolean getEntradaAutorizada() {
        return entradaAutorizada;
    }

    public void setEntradaAutorizada(Boolean entradaAutorizada) {
        this.entradaAutorizada = entradaAutorizada;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
