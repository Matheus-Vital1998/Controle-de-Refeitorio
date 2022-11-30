package Domain;

import java.time.LocalDateTime;

public class Reserva {
    private Integer id;
    private Usuario usuario;
    private Cardapio cardapio;
    private LocalDateTime horario;

    public Reserva() {
    }

    public Reserva(Integer id, Usuario usuario, Cardapio cardapio, LocalDateTime horario) {
        this.id = id;
        this.usuario = usuario;
        this.cardapio = cardapio;
        this.horario = horario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
    
    
}
