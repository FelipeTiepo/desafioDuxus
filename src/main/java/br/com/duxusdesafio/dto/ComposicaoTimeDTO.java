package br.com.duxusdesafio.dto;

import br.com.duxusdesafio.models.ComposicaoTime;
import br.com.duxusdesafio.models.Integrante;
import br.com.duxusdesafio.models.Time;


public class ComposicaoTimeDTO {

    private Long id;

    private Long time;

    private Long integrante;

    public ComposicaoTimeDTO(ComposicaoTime entity) {
        id = entity.getId();
        time = entity.getTime().getId();
        integrante = entity.getIntegrante().getId();
    }

    public ComposicaoTimeDTO(){

    }

    public ComposicaoTimeDTO(Long id, Long time, Long integrante) {
        this.id = id;
        this.time = time;
        this.integrante = integrante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Long integrante) {
        this.integrante = integrante;
    }
}