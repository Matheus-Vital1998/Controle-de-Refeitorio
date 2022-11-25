package Domain;

import java.time.LocalTime;

public class Refeicao {
    private Integer id;
    private String nome;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private LocalTime horarioLimiteReserva;

    public Refeicao() {
    }

    public Refeicao(Integer id, String nome, LocalTime horarioInicio, LocalTime horarioFim, LocalTime horarioLimiteReserva) {
        this.id = id;
        this.nome = nome;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.horarioLimiteReserva = horarioLimiteReserva;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public LocalTime getHorarioLimiteReserva() {
        return horarioLimiteReserva;
    }

    public void setHorarioLimiteReserva(LocalTime horarioLimiteReserva) {
        this.horarioLimiteReserva = horarioLimiteReserva;
    }
    
    
}
