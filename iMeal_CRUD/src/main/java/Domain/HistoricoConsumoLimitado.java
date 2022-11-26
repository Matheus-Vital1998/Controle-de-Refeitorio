/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Matheus
 * Criado para preencher a tabela do relatório
 */
public class HistoricoConsumoLimitado {    
    private String ra;
    private String nome;    
    private String refeicao;
    private LocalDate data;
    private String descricao;    
    private LocalTime horarioChegada;
    private Boolean entradaAutorizada;
    private String motivo;

    public HistoricoConsumoLimitado() {
    }

    public HistoricoConsumoLimitado(String ra, String nome, String refeicao, LocalDate data, String descricao, LocalTime horarioChegada, Boolean entradaAutorizada, String motivo) {
        this.ra = ra;
        this.nome = nome;
        this.refeicao = refeicao;
        this.data = data;
        this.descricao = descricao;
        this.horarioChegada = horarioChegada;
        this.entradaAutorizada = entradaAutorizada;
        this.motivo = motivo;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRefeicao() {
        return refeicao;
    }

    public void setRefeicao(String refeicao) {
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
    
}
